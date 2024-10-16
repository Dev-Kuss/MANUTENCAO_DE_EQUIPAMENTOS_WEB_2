package com.tads.me.service;

import com.tads.me.entity.Solicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository repository;

    @Transactional
    public Solicitacao createSolicitacao(SolicitacaoRequestDTO data) {
        Solicitacao solicitacao = Solicitacao.builder()
                .dataHora(data.dataHora())
                .descricaoEquipamento(data.descricaoEquipamento())
                .estado(data.estado())
                .categoria(null)  // Atribuir corretamente a Categoria
                .cliente(null)    // Atribuir corretamente o Cliente
                .build();
        repository.save(solicitacao);
        return solicitacao;
    }

    public List<SolicitacaoResponseDTO> listarSolicitacoes() {
        List<Solicitacao> solicitacoes = repository.findAll();
        return solicitacoes.stream()
                .map(SolicitacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Solicitacao> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Solicitacao> updateSolicitacao(Long id, SolicitacaoRequestDTO data) {
        Optional<Solicitacao> solicitacaoOptional = repository.findById(id);
        if (solicitacaoOptional.isPresent()) {
            Solicitacao solicitacao = solicitacaoOptional.get();
            solicitacao.setDescricaoEquipamento(data.descricaoEquipamento());
            solicitacao.setEstado(data.estado());
            repository.save(solicitacao);
            return Optional.of(solicitacao);
        }
        return Optional.empty();
    }
}
