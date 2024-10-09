package com.tads.me.domain.cliente;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "cliente")
@Entity(name = "cliente")

@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    public Cliente(ClienteRequestDTO data) {
        this.nome = data.nome();
    }

    public Cliente() {
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
