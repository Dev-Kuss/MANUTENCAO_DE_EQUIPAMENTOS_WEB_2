package com.tads.me.dto;

import java.time.LocalDateTime;

public record HistoricoSolicitacaoRequestDTO(
        Long idSolicitacao,
        LocalDateTime dataHora,
        String estadoAnterior,
        String estadoAtual,
        Long idFuncionario
) {}
