package com.tads.me.entity;

import com.tads.me.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@EqualsAndHashCode(callSuper = true)  // Usando equals e hashcode da classe pai
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends User {  // Cliente herda de User

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

    public Cliente(ClienteRequestDTO data) {
        this.setEmail(data.email());  // Atribui ao atributo de User
        this.setPassword(data.senha());  // Atribui ao atributo de User
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
    }
}
