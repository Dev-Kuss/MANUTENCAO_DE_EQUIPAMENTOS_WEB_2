package com.tads.me.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public Cliente(ClienteRequestDTO data) {
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.email = data.email();
        this.cep = data.cep();
        this.logradouro = data.logradouro();
        this.complemento = data.complemento();
        this.unidade = data.unidade();
        this.bairro = data.bairro();
        this.localidade = data.localidade();
        this.uf = data.uf();
        this.estado = data.estado();
        this.regiao = data.regiao();
        this.ibge = data.ibge();
        this.gia = data.gia();
        this.ddd = data.ddd();
        this.siafi = data.siafi();
    }
}
