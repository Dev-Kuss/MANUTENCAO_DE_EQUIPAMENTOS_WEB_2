package com.tads.me.dto;

import com.tads.me.entity.CategoriaEquipamento;

public record CategoriaEquipamentoResponseDTO(
        Long id,
        String nome_categoria
) {

    public CategoriaEquipamentoResponseDTO(CategoriaEquipamento categoriaEquipamento) {
        this(
                categoriaEquipamento.getId(),
                categoriaEquipamento.getNome_categoria()
        );
    }
}