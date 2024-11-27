package com.tads.me.dto;
import java.time.LocalDateTime;
import java.util.UUID;

public record SolicitacaoRequestDTO(
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String descricaoDefeito,
        String estado,
        LocalDateTime dataPagamento,
        LocalDateTime dataHoraFinalizacao,
        Long categoriaId,
        UUID clienteId,
        UUID responsavelId
) {}
