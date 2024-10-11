package com.tads.me.domain.categoriaequipamento;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "categoria_equipamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEquipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    public CategoriaEquipamento(CategoriaEquipamentoRequestDTO data){
        this.nome = data.getNome();
    }
}
