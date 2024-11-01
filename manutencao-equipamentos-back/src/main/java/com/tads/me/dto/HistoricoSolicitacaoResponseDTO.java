package com.tads.me.dto;

import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;

import java.time.LocalDateTime;

public record HistoricoSolicitacaoResponseDTO(
        Long id,
        Solicitacao solicitacao,
        LocalDateTime dataHora,
        String estadoAnterior,
        String estadoAtual,
        Funcionario funcionario
) {
    public HistoricoSolicitacaoResponseDTO(HistoricoSolicitacao historico) {
        this(
                historico.getId(),
                historico.getSolicitacao(),
                historico.getDataHora(),
                historico.getEstadoAnterior(),
                historico.getEstadoAtual(),
                historico.getFuncionario()
        );
    }
}
