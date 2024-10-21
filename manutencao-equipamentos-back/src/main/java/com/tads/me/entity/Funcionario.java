package com.tads.me.entity;

import com.tads.me.dto.FuncionarioRequestDTO;
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

    // Relacionamento 1:1 com User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Funcionario(FuncionarioRequestDTO data, User user) {
        this.email = data.email();
        this.nome = data.nome();
        this.dataNascimento = data.dataNascimento();
        this.senha = data.senha();
        this.user = user;
    }
}
