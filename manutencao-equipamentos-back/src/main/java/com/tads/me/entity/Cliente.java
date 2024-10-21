package com.tads.me.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tads.me.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "cliente")
@Entity(name = "cliente")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senhaHash;
    private String salt;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    // Relacionamento 1:1 com User
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Cliente(ClienteRequestDTO data, User user) {
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.email = data.email();
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
