package com.tads.me.dto;

import com.tads.me.entity.Pagamento;
import com.tads.me.entity.Solicitacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoResponseDTO(
        Long id,
        Solicitacao solicitacao,
        BigDecimal valor,
        LocalDateTime dataHoraPagamento
) {
    public PagamentoResponseDTO(Pagamento pagamento) {
        this(
                pagamento.getId(),
                pagamento.getSolicitacao(),
                pagamento.getValor(),
                pagamento.getDataHoraPagamento()
        );
    }
}
