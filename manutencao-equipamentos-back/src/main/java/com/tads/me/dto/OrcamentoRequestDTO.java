package com.tads.me.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrcamentoRequestDTO(
        BigDecimal valor,
        String descricao,
        LocalDateTime dataHora,
        Boolean aprovado,
        Long solicitacaoId,
        UUID funcionarioId
) {}
