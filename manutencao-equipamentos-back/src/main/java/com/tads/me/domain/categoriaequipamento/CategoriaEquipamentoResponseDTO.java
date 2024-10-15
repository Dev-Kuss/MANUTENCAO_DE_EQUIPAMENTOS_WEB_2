package com.tads.me.domain.categoriaequipamento;

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
