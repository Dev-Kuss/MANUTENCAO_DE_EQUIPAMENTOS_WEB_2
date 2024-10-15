package com.tads.me.domain.historicosolicitacao;

import java.time.LocalDateTime;

public record HistoricoSolicitacaoRequestDTO(
        Long idSolicitacao,
        LocalDateTime dataHora,
        String estadoAnterior,
        String estadoAtual,
        Long idFuncionario
) {}
