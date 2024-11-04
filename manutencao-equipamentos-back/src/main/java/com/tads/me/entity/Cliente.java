package com.tads.me.entity;

import com.tads.me.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String cpf;
    private String email;
    private String nome;
    private String telefone;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;


    public Cliente(ClienteRequestDTO data, User user) {
        this.cpf = data.cpf();
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.cep = data.cep();
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.estado = data.estado();
        this.user = user;
    }
}