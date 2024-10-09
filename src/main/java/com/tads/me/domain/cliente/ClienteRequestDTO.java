package com.tads.me.domain.cliente;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        Integer cep,
        String logradouro,
        String complemento,
        String unidade,
        String bairro,
        String localidade,
        String uf,
        String estado,
        String regiao,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {
}
