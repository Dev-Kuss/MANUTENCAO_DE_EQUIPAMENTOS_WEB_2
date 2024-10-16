package com.tads.me.dto;

import java.time.LocalDateTime;

public record SolicitacaoRequestDTO(
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String estado,
        Long idCategoria,
        Long idCliente
) {}
