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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(ClienteRequestDTO data) {
        this.nome = data.nome();
        this.cpf = data.cpf();
        this.email = data.email();
        this.telefone = data.telefone();
    }
}
