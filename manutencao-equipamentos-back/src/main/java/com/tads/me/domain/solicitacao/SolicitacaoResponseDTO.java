package com.tads.me.domain.solicitacao;

import com.tads.me.domain.categoriaequipamento.CategoriaEquipamento;
import com.tads.me.domain.cliente.Cliente;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record SolicitacaoResponseDTO(
        Long id_solicitacao,
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String estado,
        CategoriaEquipamento categoria,
        Cliente cliente
) {
    public SolicitacaoResponseDTO(Solicitacao solicitacao) {
        this(
                solicitacao.getId_solicitacao(),
                solicitacao.getDataHora(),
                solicitacao.getDescricaoEquipamento(),
                solicitacao.getEstado(),
                solicitacao.getCategoria(),
                solicitacao.getCliente()
        );
    }
}