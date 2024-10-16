package com.tads.me.dto;

import com.tads.me.entity.CategoriaEquipamento;
import com.tads.me.entity.Cliente;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tads.me.entity.Solicitacao;

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