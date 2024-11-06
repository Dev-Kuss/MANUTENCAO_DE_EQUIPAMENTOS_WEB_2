package com.tads.me.dto;

import com.tads.me.entity.Orcamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public record OrcamentoResponseDTO(
        Long id,
        String descricao,
        Long solicitacaoId,
        String solicitacaoDescricao,
        BigDecimal valor,
        LocalDateTime dataHora,
        String funcionarioNome
) {
    public OrcamentoResponseDTO(Orcamento orcamento) {
        this(
                orcamento.getId(),
                orcamento.getDescricao(),
                orcamento.getSolicitacao().getIdSolicitacao(),
                orcamento.getSolicitacao().getDescricaoEquipamento(),
                orcamento.getValor(),
                orcamento.getDataHora(),
                orcamento.getFuncionario() != null ? orcamento.getFuncionario().getNome() : null
        );
    }
}
