package com.tads.me.dto;

import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;

import java.time.LocalDateTime;

public record HistoricoSolicitacaoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String descricao,
        String nomeFuncionario,
        String nomeCliente,
        Long solicitacaoId
) {
    public HistoricoSolicitacaoResponseDTO(HistoricoSolicitacao historico) {
        this(
                historico.getId(),
                historico.getDataHora(),
                historico.getDescricao(),
                historico.getFuncionario().getNome(),
                historico.getSolicitacao().getCliente().getNome(),
                historico.getSolicitacao().getId_solicitacao()
        );
    }
}
