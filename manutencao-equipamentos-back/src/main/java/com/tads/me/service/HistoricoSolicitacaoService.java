package com.tads.me.service;

import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacao;
import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacaoRequestDTO;
import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacaoResponseDTO;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricoSolicitacaoService {

    @Autowired
    private HistoricoSolicitacaoRepository repository;

    @Transactional
    public HistoricoSolicitacao createHistorico(HistoricoSolicitacaoRequestDTO data) {
        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(data.dataHora())
                .estadoAnterior(data.estadoAnterior())
                .estadoAtual(data.estadoAtual())
                .solicitacao(null)  // Atribuir corretamente a solicitação
                .funcionario(null)  // Atribuir corretamente o funcionário
                .build();
        repository.save(historico);
        return historico;
    }

    public List<HistoricoSolicitacaoResponseDTO> listarHistoricos() {
        List<HistoricoSolicitacao> historicos = repository.findAll();
        return historicos.stream()
                .map(HistoricoSolicitacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<HistoricoSolicitacao> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<HistoricoSolicitacao> updateHistorico(Long id, HistoricoSolicitacaoRequestDTO data) {
        Optional<HistoricoSolicitacao> historicoOptional = repository.findById(id);
        if (historicoOptional.isPresent()) {
            HistoricoSolicitacao historico = historicoOptional.get();
            historico.setEstadoAnterior(data.estadoAnterior());
            historico.setEstadoAtual(data.estadoAtual());
            repository.save(historico);
            return Optional.of(historico);
        }
        return Optional.empty();
    }
}
