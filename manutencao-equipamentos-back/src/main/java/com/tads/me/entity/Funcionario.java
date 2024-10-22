package com.tads.me.entity;

import com.tads.me.dto.FuncionarioRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario extends User {

    private String nome;
    private String telefone;
    private LocalDate dataNascimento;

    // Construtor que recebe FuncionarioRequestDTO e o próprio User
    public Funcionario(FuncionarioRequestDTO data, User user) {
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.dataNascimento = data.dataNascimento();
        this.setEmail(data.email());  // Setter gerado pelo Lombok
        this.setPasswordHash(user.getPasswordHash());  // Setter gerado pelo Lombok
        this.setPasswordSalt(user.getPasswordSalt());  // Setter gerado pelo Lombok
    }
}
