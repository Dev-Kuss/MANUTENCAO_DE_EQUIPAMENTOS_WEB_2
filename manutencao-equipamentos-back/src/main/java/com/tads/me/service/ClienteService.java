package com.tads.me.service;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) throws NoSuchAlgorithmException {
        Cliente newCliente = new Cliente(data);
        String salt = gerarSalt();
        String senhaHash = hashSenhaComSalt(data.senha(), salt);
        newCliente.setSenhaHash(senhaHash);
        newCliente.setSalt(salt);
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

    @Transactional
    public String gerarSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    @Transactional
    public String hashSenhaComSalt(String senha, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String senhaComSalt = senha + salt;
        byte[] hash = md.digest(senhaComSalt.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @Transactional
    public boolean validarSenha(String senha, Cliente cliente) throws NoSuchAlgorithmException {
        String hashSenhaFornecida = hashSenhaComSalt(senha, cliente.getSalt());
        return hashSenhaFornecida.equals(cliente.getSenhaHash());
    }
}
