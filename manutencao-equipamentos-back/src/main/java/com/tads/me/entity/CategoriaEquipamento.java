package com.tads.me.entity;

import com.tads.me.dto.CategoriaEquipamentoRequestDTO;
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
    private String nome_categoria;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public CategoriaEquipamento(CategoriaEquipamentoRequestDTO data){
        this.nome_categoria = data.getNome_categoria();
        this.ativo = data.isAtivo();
    }
}
