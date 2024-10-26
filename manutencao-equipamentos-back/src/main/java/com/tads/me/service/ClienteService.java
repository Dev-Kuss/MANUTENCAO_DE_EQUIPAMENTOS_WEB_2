package com.tads.me.service;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UserRepository userRepository;

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
        if (cpfExists(data.cpf()) || emailExists(data.email())) {
            throw new IllegalArgumentException("CPF ou Email já cadastrado.");
        }

        User newUser = new User();
        newUser.setEmail(data.email());

        String salt = gerarSalt();
        String passwordHash = hashSenhaComSalt(data.senha(), salt);

        newUser.setPasswordHash(passwordHash);
        newUser.setPasswordSalt(salt);
        newUser.setRoles(new HashSet<>(Set.of("CLIENTE")));
        userRepository.save(newUser);

        Cliente newCliente = new Cliente(data, newUser);
        newCliente.setId(newUser.getId());
        return repository.save(newCliente);
    }


    @Transactional
    public Optional<Cliente> getClienteById(UUID id) {
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

    @Transactional
    public String gerarSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    @Transactional
    public String hashSenhaComSalt(String senha, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String passwordHashSalt = senha + salt;
        byte[] hash = md.digest(passwordHashSalt.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @Transactional
    public boolean validarSenha(String senha, Cliente cliente) throws NoSuchAlgorithmException {
        String hashSenhaFornecida = hashSenhaComSalt(senha, cliente.getUser().getPasswordSalt());  // Corrigido para acessar o salt do User
        return hashSenhaFornecida.equals(cliente.getUser().getPasswordHash());
    }
}
