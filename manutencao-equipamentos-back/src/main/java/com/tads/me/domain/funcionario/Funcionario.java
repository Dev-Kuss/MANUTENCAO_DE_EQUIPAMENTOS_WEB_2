package com.tads.me.domain.funcionario;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "funcionario")
@Entity(name = "funcionario")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String senha;

    public Funcionario(FuncionarioRequestDTO data) {
        this.email = data.email();
        this.nome = data.nome();
        this.dataNascimento = data.dataNascimento();
        this.senha = data.senha();
    }
}
