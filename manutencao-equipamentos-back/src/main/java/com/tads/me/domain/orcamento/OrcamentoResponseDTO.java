package com.tads.me.domain.orcamento;

import com.tads.me.domain.solicitacao.Solicitacao;
import com.tads.me.domain.funcionario.Funcionario;

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
