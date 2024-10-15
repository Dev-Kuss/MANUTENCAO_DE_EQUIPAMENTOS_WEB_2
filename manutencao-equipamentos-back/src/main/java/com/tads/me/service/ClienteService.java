package com.tads.me.service;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) {
        Cliente newCliente = new Cliente(data);
        repository.save(newCliente);
        return newCliente;
    }

    @Transactional
    public Optional<Cliente> updateCliente(Long id, ClienteRequestDTO data) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente existingCliente = clienteOptional.get();
            existingCliente.setNome(data.nome());
            existingCliente.setCpf(data.cpf());
            existingCliente.setEmail(data.email());
            existingCliente.setTelefone(data.telefone());
            repository.save(existingCliente);
            return Optional.of(existingCliente);
        }
        return Optional.empty();
    }
}
