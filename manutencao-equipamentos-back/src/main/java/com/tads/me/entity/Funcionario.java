package com.tads.me.entity;

import com.tads.me.dto.FuncionarioRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    private String email;
    
    private String nome;

    private String telefone;
    
    private LocalDate dataNascimento;


    public Funcionario(FuncionarioRequestDTO data, User user) {
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.dataNascimento = data.dataNascimento();
        this.user = user;
    }
}
