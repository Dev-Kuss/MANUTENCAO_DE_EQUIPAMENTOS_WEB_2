package com.tads.me.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Table(name = "cliente")
@Entity(name = "cliente")
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

}
