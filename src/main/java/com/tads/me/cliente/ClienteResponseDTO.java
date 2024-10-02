package com.tads.me.cliente;

public record ClienteResponseDTO(Long id, String nome) {
    public ClienteResponseDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome());
    }
}
