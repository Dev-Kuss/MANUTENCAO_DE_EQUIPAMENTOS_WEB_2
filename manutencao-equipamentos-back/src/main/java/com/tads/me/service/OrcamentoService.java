package com.tads.me.service;

import com.tads.me.domain.orcamento.Orcamento;
import com.tads.me.domain.orcamento.OrcamentoRequestDTO;
import com.tads.me.domain.orcamento.OrcamentoResponseDTO;
import com.tads.me.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    @Transactional
    public Orcamento createOrcamento(OrcamentoRequestDTO data) {
        Orcamento orcamento = Orcamento.builder()
                .valor(data.valor())
                .dataHora(data.dataHora())
                .solicitacao(null)  // Atribuir corretamente a solicitação
                .funcionario(null)  // Atribuir corretamente o funcionário
                .build();
        repository.save(orcamento);
        return orcamento;
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
