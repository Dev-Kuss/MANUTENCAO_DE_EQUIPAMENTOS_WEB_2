package com.tads.me.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrcamentoDTO(
    Long id,
    Long idSolicitacao,
    BigDecimal valor,
    LocalDateTime dataHora,
    Long idFuncionario
) {} 