package com.tads.me.domain.solicitacao;

import com.tads.me.domain.categoriaequipamento.CategoriaEquipamento;
import com.tads.me.domain.cliente.Cliente;

import java.time.LocalDateTime;

public record SolicitacaoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String estado,
        CategoriaEquipamento categoria,
        Cliente cliente
) {
    public SolicitacaoResponseDTO(Solicitacao solicitacao) {
        this(
                solicitacao.getId(),
                solicitacao.getDataHora(),
                solicitacao.getDescricaoEquipamento(),
                solicitacao.getEstado(),
                solicitacao.getCategoria(),
                solicitacao.getCliente()
        );
    }
}
