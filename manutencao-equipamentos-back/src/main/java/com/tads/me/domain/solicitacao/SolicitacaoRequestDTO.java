package com.tads.me.domain.solicitacao;

import java.time.LocalDateTime;

public record SolicitacaoRequestDTO(
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String estado,
        Long idCategoria,
        Long idCliente
) {}
