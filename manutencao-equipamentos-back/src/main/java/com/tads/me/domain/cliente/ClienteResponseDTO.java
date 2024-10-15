package com.tads.me.domain.cliente;

import com.tads.me.domain.endereco.Endereco;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        Endereco endereco
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getEndereco()
        );
    }
}
