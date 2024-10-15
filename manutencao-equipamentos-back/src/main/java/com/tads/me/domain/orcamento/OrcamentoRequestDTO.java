package com.tads.me.domain.orcamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrcamentoRequestDTO(
        Long idSolicitacao,
        BigDecimal valor,
        LocalDateTime dataHora,
        Long idFuncionario
) {}
