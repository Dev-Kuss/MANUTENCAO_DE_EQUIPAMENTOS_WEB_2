package com.tads.me.domain.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRequestDTO(
        Long idSolicitacao,
        BigDecimal valor,
        LocalDateTime dataHoraPagamento
) {}
