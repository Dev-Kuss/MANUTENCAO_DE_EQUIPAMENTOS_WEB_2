package com.tads.me.service;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) {
        Cliente newCliente = new Cliente(data);  // Using the constructor that accepts ClienteRequestDTO
        repository.save(newCliente);
        return newCliente;
    }
}
