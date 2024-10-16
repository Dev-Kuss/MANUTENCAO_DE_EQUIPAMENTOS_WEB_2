package com.tads.me.dto;

import com.tads.me.entity.Cliente;
import com.tads.me.entity.Endereco;

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
