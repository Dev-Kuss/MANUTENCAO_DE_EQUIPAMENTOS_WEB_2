package com.tads.me.service;

import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.repository.FuncionarioRepository;
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
                .categoria(categoriaRepository.findById(data.categoriaId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada")))
                .cliente(clienteRepository.findById(data.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")))
                .responsavel(null)
                .build();

        repository.save(solicitacao);

        // Cria o primeiro histórico da solicitação
        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao("Solicitação criada")
                .cliente(clienteRepository.findById(data.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")))
                .solicitacao(solicitacao)
                .build();

        historicoRepository.save(historico);
        
        return new SolicitacaoResponseDTO(solicitacao);
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoes() {
        List<Solicitacao> solicitacoes = repository.findAll();
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
                    // Atualiza os dados da solicitação
                    solicitacao.setDescricaoEquipamento(data.descricaoEquipamento());
                    solicitacao.setDescricaoDefeito(data.descricaoDefeito());
                    solicitacao.setEstado(data.estado());
                    solicitacao.setResponsavel(funcionarioRepository.findById(data.responsavelId()).orElseThrow(() -> new RuntimeException("Funcionario não encontrado")));
                    solicitacao.setDataPagamento(data.dataPagamento());
                    solicitacao.setDataHoraFinalizacao(data.dataHoraFinalizacao());

                    if (data.categoriaId() != null) {
                        categoriaRepository.findById(data.categoriaId())
                                .ifPresent(solicitacao::setCategoria);
                    }

                    // Registra a alteração no histórico
                    HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                            .dataHora(LocalDateTime.now())
                            .descricao("Solicitação atualizada")
                            .funcionario(funcionarioRepository.findById(data.responsavelId()).orElseThrow(() -> new RuntimeException("Funcionario não encontrado")))
                            .solicitacao(solicitacao)
                            .build();

                    historicoRepository.save(historico);
                    repository.save(solicitacao);
                    
                    return new SolicitacaoResponseDTO(solicitacao);
                });
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoesPorUsuario(UUID usuarioId) {
        return repository.findByClienteId(usuarioId).stream()
                .map(SolicitacaoResponseDTO::new)
                .collect(Collectors.toList());
    }
}
