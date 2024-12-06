package com.tads.me.dto;

import com.tads.me.entity.HistoricoSolicitacao;


import java.time.LocalDateTime;

public record HistoricoSolicitacaoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String descricao,
        String destinoRedirecionamento,
        String nomeFuncionario,
        Long solicitacaoId
) {
    public HistoricoSolicitacaoResponseDTO(HistoricoSolicitacao historico) {
        this(
                historico.getId(),
                historico.getDataHora(),
                historico.getDescricao(),
                historico.getDestinoRedirecionamento(),
                historico.getFuncionario() != null ? historico.getFuncionario().getNome() : null,
                historico.getSolicitacao().getIdSolicitacao()
        );
    }
}
