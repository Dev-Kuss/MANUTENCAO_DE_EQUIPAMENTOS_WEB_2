package com.tads.me.entity;

import com.tads.me.dto.FuncionarioRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@EqualsAndHashCode(callSuper = true)  // Usando equals e hashcode da classe pai
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario extends User {  // Funcionario herda de User

    private String nome;
    private LocalDate dataNascimento;

    public Funcionario(FuncionarioRequestDTO data) {
        this.setEmail(data.email());  // Atribui ao atributo de User
        this.setPassword(data.senha());  // Atribui ao atributo de User
        this.nome = data.nome();
        this.dataNascimento = data.dataNascimento();
    }
}
