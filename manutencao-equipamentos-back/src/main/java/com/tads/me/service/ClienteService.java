package com.tads.me.service;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.entity.User;
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
import java.util.Set;
import java.util.UUID;

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
        if (cpfExists(data.cpf()) || emailExists(data.email())) {
            throw new IllegalArgumentException("CPF ou Email já cadastrado.");
        }

        User newUser = new User();
        newUser.setEmail(data.email());

        // Gerar salt e hash para a senha
        String salt = gerarSalt();
        String password = hashSenhaComSalt(data.senha(), salt);

        newUser.setPasswordHash(password);
        newUser.setPasswordSalt(salt);
        newUser.setRoles(Set.of("ROLE_CLIENTE"));

        // Criar o Cliente com o User relacionado
        Cliente newCliente = new Cliente(data, newUser);
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
        String passordHashSalt = senha + salt;
        byte[] hash = md.digest(passordHashSalt.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @Transactional
    public boolean validarSenha(String senha, Cliente cliente) throws NoSuchAlgorithmException {
        String hashSenhaFornecida = hashSenhaComSalt(senha, cliente.getPasswordSalt());
        return hashSenhaFornecida.equals(cliente.getPasswordHash());
    }

}
