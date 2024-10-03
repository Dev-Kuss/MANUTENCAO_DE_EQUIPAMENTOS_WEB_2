package com.tads.me.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cliente")
@Entity(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
    public void setNome(String nome) {
        this.nome = nome;
    }

}
