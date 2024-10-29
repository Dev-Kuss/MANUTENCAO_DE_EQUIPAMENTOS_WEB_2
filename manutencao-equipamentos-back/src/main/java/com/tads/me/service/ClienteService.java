package com.tads.me.service;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.repository.UserRepository;
import com.tads.me.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.tads.me.util.GerarSenhaAleatoria.gerarSenhaAleatoria;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SHA256PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public boolean cpfExists(String cpf) {
        return repository.findByCpf(cpf).isPresent();
    }

    @Transactional
    public boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) {
        if (cpfExists(data.cpf()) || emailExists(data.email())) {
            throw new IllegalArgumentException("CPF ou Email já cadastrado.");
        }

        User newUser = new User();
        newUser.setEmail(data.email());

        String senhaOriginal = data.senha() != null ? data.senha() : gerarSenhaAleatoria();
        String passwordHashSalt = passwordEncoder.encode(senhaOriginal); // Inclui o salt

        newUser.setPasswordHashSalt(passwordHashSalt);
        newUser.setRoles(new HashSet<>(Set.of("USER")));
        userRepository.save(newUser);

        Cliente newCliente = new Cliente(data, newUser);
        newCliente.setId(newUser.getId());
        repository.save(newCliente);

        enviarEmailComSenha(data.nome(), data.email(), senhaOriginal);

        return newCliente;
    }

    @Transactional
    public void enviarEmailComSenha(String nome, String emailDestino, String senha) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDestino);
            message.setSubject("Sua senha de acesso ao sistema");
            message.setText("Olá " + nome +",\n\nSeu cadastro foi realizado com sucesso! Sua senha de acesso é: " + senha + "\n\nPor favor, mantenha-a em segurança.");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
