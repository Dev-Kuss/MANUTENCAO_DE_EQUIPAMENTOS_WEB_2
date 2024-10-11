package com.tads.me.domain.categoriaequipamento;

public record CategoriaEquipamentoResponseDTO(
        Long id,
        String nome
) {

    public CategoriaEquipamentoResponseDTO(CategoriaEquipamento categoriaEquipamento) {
        this(
                categoriaEquipamento.getId(),
                categoriaEquipamento.getNome()
        );
    }
}
