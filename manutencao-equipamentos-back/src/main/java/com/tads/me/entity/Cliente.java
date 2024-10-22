package com.tads.me.entity;

import com.tads.me.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends User {

    private String cpf;
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
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.setEmail(data.email());
        this.telefone = data.telefone();
        this.cep = data.cep();
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.estado = data.estado();
        this.setPasswordHash(user.getPasswordHash());
        this.setPasswordSalt(user.getPasswordSalt());
    }
}