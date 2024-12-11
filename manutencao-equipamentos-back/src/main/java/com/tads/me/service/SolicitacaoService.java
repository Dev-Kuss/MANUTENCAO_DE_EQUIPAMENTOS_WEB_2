package com.tads.me.service;

import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;
import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;

import jakarta.persistence.EntityNotFoundException;

import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository repository;

    @Autowired
    private HistoricoSolicitacaoRepository historicoRepository;

    @Autowired
    private CategoriaEquipamentoRepository categoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public SolicitacaoResponseDTO createSolicitacao(SolicitacaoRequestDTO data) {

        Solicitacao solicitacao = Solicitacao.builder()
                .dataHora(data.dataHora())
                .descricaoEquipamento(data.descricaoEquipamento())
                .descricaoDefeito(data.descricaoDefeito())
                .estado(data.estado())
                .dataPagamento(data.dataPagamento())
                .dataHoraFinalizacao(data.dataHoraFinalizacao())
                .categoria(categoriaRepository.findById(data.idCategoria())
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada")))
                .cliente(clienteRepository.findById(data.idCliente())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado")))
                .responsavel(null)
                .build();

        repository.save(solicitacao);

        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao("Solicitação criada")
                // .cliente(clienteRepository.findById(data.idCliente())
                //         .orElseThrow(() -> new RuntimeException("Cliente não encontrado")))
                .solicitacao(solicitacao)
                .build();

        historicoRepository.save(historico);

        return new SolicitacaoResponseDTO(solicitacao);
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoes() {
        List<Solicitacao> solicitacoes = repository.findAllByOrderByIdDesc();
        return solicitacoes.stream()
                .map(SolicitacaoResponseDTO::new)
                .collect(Collectors.toList());
    }
    

    public Optional<SolicitacaoResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(SolicitacaoResponseDTO::new);
    }

    @Transactional
    public Optional<SolicitacaoResponseDTO> updateSolicitacao(Long id, SolicitacaoRequestDTO data) {
        return repository.findById(id)
                .map(solicitacao -> {
                    // Atualizar campos apenas se não forem nulos
                    Optional.ofNullable(data.descricaoEquipamento()).ifPresent(solicitacao::setDescricaoEquipamento);
                    Optional.ofNullable(data.descricaoDefeito()).ifPresent(solicitacao::setDescricaoDefeito);
                    Optional.ofNullable(data.estado()).ifPresent(solicitacao::setEstado);
                    Optional.ofNullable(data.dataPagamento()).ifPresent(solicitacao::setDataPagamento);
                    Optional.ofNullable(data.dataHoraFinalizacao()).ifPresent(solicitacao::setDataHoraFinalizacao);

                    // Atualizar categoria, se fornecida
                    Optional.ofNullable(data.idCategoria())
                            .flatMap(categoriaRepository::findById)
                            .ifPresent(solicitacao::setCategoria);

                    // Atualizar responsável
                    if (data.idResponsavel() != null) {
                        solicitacao.setResponsavel(buscarFuncionario(data.idResponsavel()));
                    }

                    // Registrar histórico
                    registrarHistorico(solicitacao, data.idResponsavel(), "Solicitação atualizada");

                    // Salvar alterações
                    repository.save(solicitacao);

                    return new SolicitacaoResponseDTO(solicitacao);
                });
    }

    @Transactional
    public Optional<SolicitacaoResponseDTO> patchSolicitacao(Long id, Map<String, Object> updates) {
        return repository.findById(id).map(solicitacao -> {
            updates.forEach((key, value) -> updateField(solicitacao, key, value));

            // UUID responsavelId = updates.containsKey("idResponsavel") 
            //         ? UUID.fromString((String) updates.get("idResponsavel")) 
            //         : null;

            // registrarHistorico(solicitacao, responsavelId, "Solicitação atualizada parcialmente");
            repository.save(solicitacao);
            return new SolicitacaoResponseDTO(solicitacao);
        });
    }

    private void updateField(Solicitacao solicitacao, String key, Object value) {
        System.out.println("Atualizando campo: " + key + " com valor: " + value);
        switch (key) {
            case "descricaoEquipamento" -> solicitacao.setDescricaoEquipamento((String) value);
            case "descricaoDefeito" -> solicitacao.setDescricaoDefeito((String) value);
            case "estado" -> solicitacao.setEstado((String) value);
            case "dataPagamento" -> {
                if (value == null) {
                    solicitacao.setDataPagamento(null);
                } else if (value instanceof String) {
                    solicitacao.setDataPagamento(LocalDateTime.parse((String) value));
                } else if (value instanceof LocalDateTime) {
                    solicitacao.setDataPagamento((LocalDateTime) value);
                } else {
                    throw new IllegalArgumentException("Formato de 'dataPagamento' inválido: " + value);
                }
            }
            case "dataHoraFinalizacao" -> {
                if (value == null) {
                    solicitacao.setDataHoraFinalizacao(null);
                } else if (value instanceof String) {
                    solicitacao.setDataHoraFinalizacao(LocalDateTime.parse((String) value));
                } else if (value instanceof LocalDateTime) {
                    solicitacao.setDataHoraFinalizacao((LocalDateTime) value);
                } else {
                    throw new IllegalArgumentException("Formato de 'dataHoraFinalizacao' inválido: " + value);
                }
            }
            case "idCategoria" -> {
                if (value == null || !(value instanceof Number)) {
                    throw new IllegalArgumentException("O valor de 'idCategoria' deve ser um número");
                }
                categoriaRepository.findById(((Number) value).longValue())
                        .ifPresentOrElse(
                            solicitacao::setCategoria,
                            () -> { throw new IllegalArgumentException("Categoria não encontrada com o ID fornecido"); }
                        );
            }
            case "idResponsavel" -> {
                if (value == null || !(value instanceof String)) {
                    throw new IllegalArgumentException("O valor de 'idResponsavel' deve ser uma string válida");
                }
                try {
                    UUID responsavelId = UUID.fromString((String) value);
                    solicitacao.setResponsavel(buscarFuncionario(responsavelId));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("ID do responsável inválido: " + value, e);
                }
            }
            default -> throw new IllegalArgumentException("Campo não suportado ou inválido: " + key);
        }
    }
    

    private Funcionario buscarFuncionario(UUID idResponsavel) {
        return funcionarioRepository.findById(idResponsavel)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + idResponsavel));
    }

    private void registrarHistorico(Solicitacao solicitacao, UUID idResponsavel, String descricao) {
        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao(descricao)
                .funcionario(buscarFuncionario(idResponsavel))
                .solicitacao(solicitacao)
                .build();
        historicoRepository.save(historico);
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoesPorUsuario(UUID usuarioId) {
        return repository.findByClienteId(usuarioId).stream()
                .map(SolicitacaoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
