package com.tads.me.dto;

import java.util.UUID;

public record HistoricoSolicitacaoRequestDTO(
    String descricao,
    String destinoRedirecionamento,
    UUID idFuncionario,
    UUID idCliente,
    Long idSolicitacao
) {}
