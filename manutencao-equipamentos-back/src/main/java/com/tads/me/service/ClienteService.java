package com.tads.me.service;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional(readOnly = true)
    public boolean cpfExists(String cpf) {
        return repository.findByCpf(cpf).isPresent();
    }

    @Transactional
    public boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) throws NoSuchAlgorithmException {

        if (cpfExists(data.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        if (emailExists(data.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Cliente newCliente = new Cliente(data);
        repository.save(newCliente);
        return newCliente;
    }

    @Transactional
    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
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
            existingCliente.setCep(data.cep());
            existingCliente.setLogradouro(data.logradouro());
            existingCliente.setNumero(data.numero());
            existingCliente.setComplemento(data.complemento());
            existingCliente.setBairro(data.bairro());
            existingCliente.setCidade(data.cidade());
            existingCliente.setEstado(data.estado());
            repository.save(existingCliente);
            return Optional.of(existingCliente);
        }
        return Optional.empty();
    }
}
