package com.tads.me.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrcamentoRequestDTO(
        Long idSolicitacao,
        BigDecimal valor,
        LocalDateTime dataHora,
        Long idFuncionario
) {}
