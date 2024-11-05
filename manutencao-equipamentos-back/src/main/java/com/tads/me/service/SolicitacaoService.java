package com.tads.me.service;

import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.dto.HistoricoSolicitacaoResponseDTO;
import com.tads.me.dto.OrcamentoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

    @Transactional
    public SolicitacaoResponseDTO createSolicitacao(SolicitacaoRequestDTO data) {
        var categoria = categoriaRepository.findById(data.idCategoria())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Solicitacao solicitacao = Solicitacao.builder()
                .dataHora(data.dataHora())
                .descricaoEquipamento(data.descricaoEquipamento())
                .descricaoDefeito(data.descricaoDefeito())
                .estado(data.estado())
                .dataPagamento(data.dataPagamento())
                .dataHoraFinalizacao(data.dataHoraFinalizacao())
                .categoria(categoria)
                .cliente(data.cliente())
                .responsavel(data.responsavel())
                .build();

        repository.save(solicitacao);

        // Cria o primeiro histórico da solicitação
        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao("Solicitação criada")
                .cliente(data.cliente())
                .solicitacao(solicitacao)
                .build();

        historicoRepository.save(historico);
        
        return convertToResponseDTO(solicitacao);
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoes() {
        List<Solicitacao> solicitacoes = repository.findAll();
        return solicitacoes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<SolicitacaoResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::convertToResponseDTO);
    }

    @Transactional
    public Optional<SolicitacaoResponseDTO> updateSolicitacao(Long id, SolicitacaoRequestDTO data) {
        return repository.findById(id)
                .map(solicitacao -> {
                    // Atualiza os dados da solicitação
                    solicitacao.setDescricaoEquipamento(data.descricaoEquipamento());
                    solicitacao.setDescricaoDefeito(data.descricaoDefeito());
                    solicitacao.setEstado(data.estado());
                    solicitacao.setResponsavel(data.responsavel());
                    solicitacao.setDataPagamento(data.dataPagamento());
                    solicitacao.setDataHoraFinalizacao(data.dataHoraFinalizacao());

                    if (data.idCategoria() != null) {
                        categoriaRepository.findById(data.idCategoria())
                                .ifPresent(solicitacao::setCategoria);
                    }

                    // Registra a alteração no histórico
                    HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                            .dataHora(LocalDateTime.now())
                            .descricao("Solicitação atualizada")
                            .funcionario(data.responsavel())
                            .solicitacao(solicitacao)
                            .build();

                    historicoRepository.save(historico);
                    repository.save(solicitacao);
                    
                    return convertToResponseDTO(solicitacao);
                });
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoesPorUsuario(UUID usuarioId) {
        return repository.findByClienteId(usuarioId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private SolicitacaoResponseDTO convertToResponseDTO(Solicitacao solicitacao) {
        String clienteNome = solicitacao.getCliente() != null ? solicitacao.getCliente().getNome() : null;
        String categoriaNome = solicitacao.getCategoria() != null ? solicitacao.getCategoria().getNome_categoria() : null;
        String responsavelNome = solicitacao.getResponsavel() != null ? solicitacao.getResponsavel().getNome() : null;
        
        List<HistoricoSolicitacaoResponseDTO> historicos = solicitacao.getHistoricos().stream()
            .map(historico -> new HistoricoSolicitacaoResponseDTO(
                historico.getId(),
                historico.getDataHora(),
                historico.getDescricao(),
                historico.getFuncionario() != null ? historico.getFuncionario().getNome() : null,
                historico.getCliente() != null ? historico.getCliente().getNome() : null,
                historico.getSolicitacao().getIdSolicitacao()
            ))
            .collect(Collectors.toList());
            
        List<OrcamentoResponseDTO> orcamentos = solicitacao.getOrcamentos().stream()
            .map(orcamento -> new OrcamentoResponseDTO(
                orcamento.getId(),
                orcamento.getValor(),
                orcamento.getDataHora(),
                orcamento.getSolicitacao().getIdSolicitacao(),
                orcamento.getFuncionario() != null ? orcamento.getFuncionario().getNome() : null,
                orcamento.getDescricao()
            ))
            .collect(Collectors.toList());

        return new SolicitacaoResponseDTO(
            solicitacao.getIdSolicitacao(),
            solicitacao.getDescricaoEquipamento(),
            solicitacao.getDescricaoDefeito(),
            solicitacao.getDataHora(),
            solicitacao.getDataHoraFinalizacao(),
            solicitacao.getDataPagamento(),
            solicitacao.getEstado(),
            clienteNome,
            categoriaNome,
            responsavelNome,
            historicos,
            orcamentos
        );
    }
}
