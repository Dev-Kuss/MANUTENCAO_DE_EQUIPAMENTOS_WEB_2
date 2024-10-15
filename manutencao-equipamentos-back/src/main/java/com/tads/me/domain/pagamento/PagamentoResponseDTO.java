package com.tads.me.domain.pagamento;

import com.tads.me.domain.solicitacao.Solicitacao;

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
