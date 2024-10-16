package com.tads.me.dto;

import com.tads.me.entity.Orcamento;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrcamentoResponseDTO(
        Long id,
        Solicitacao solicitacao,
        BigDecimal valor,
        LocalDateTime dataHora,
        Funcionario funcionario
) {
    public OrcamentoResponseDTO(Orcamento orcamento) {
        this(
                orcamento.getId(),
                orcamento.getSolicitacao(),
                orcamento.getValor(),
                orcamento.getDataHora(),
                orcamento.getFuncionario()
        );
    }
}
