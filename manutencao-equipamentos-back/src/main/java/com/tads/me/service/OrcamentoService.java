package com.tads.me.service;

import com.tads.me.entity.Orcamento;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;
import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.dto.OrcamentoRequestDTO;
import com.tads.me.dto.OrcamentoResponseDTO;
import com.tads.me.repository.OrcamentoRepository;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.repository.HistoricoSolicitacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private HistoricoSolicitacaoRepository historicoRepository;

    @Transactional
    public Orcamento createOrcamento(OrcamentoRequestDTO data) {
        if (data.solicitacaoId() == null) {
            throw new IllegalArgumentException("ID da solicitação não pode ser nulo");
        }
        if (data.funcionarioId() == null) {
            throw new IllegalArgumentException("ID do funcionário não pode ser nulo");
        }

        Solicitacao solicitacao = solicitacaoRepository.findById(data.solicitacaoId())
            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        Funcionario funcionario = funcionarioRepository.findById(data.funcionarioId())
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao("Orçamento realizado.")
                .solicitacao(solicitacao)
                .build();

        historicoRepository.save(historico);

        Orcamento orcamento = Orcamento.builder()
                .valor(data.valor())
                .dataHora(data.dataHora())
                .solicitacao(solicitacao)
                .funcionario(funcionario)
                .build();

        return repository.save(orcamento);
    }

    public List<OrcamentoResponseDTO> listarOrcamentos() {
        List<Orcamento> orcamentos = repository.findAll();
        return orcamentos.stream()
                .map(OrcamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Orcamento> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Orcamento> updateOrcamento(Long id, OrcamentoRequestDTO data) {
        Optional<Orcamento> orcamentoOptional = repository.findById(id);
        if (orcamentoOptional.isPresent()) {
            Orcamento orcamento = orcamentoOptional.get();
            orcamento.setValor(data.valor());
            repository.save(orcamento);
            return Optional.of(orcamento);
        }
        return Optional.empty();
    }
}
