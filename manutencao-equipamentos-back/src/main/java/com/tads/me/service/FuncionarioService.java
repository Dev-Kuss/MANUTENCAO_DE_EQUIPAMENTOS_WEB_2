package com.tads.me.service;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.FuncionarioRepository;
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
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    public Funcionario createFuncionario(FuncionarioRequestDTO data, User user) throws NoSuchAlgorithmException {
        // Gera salt e hash para a senha
        String salt = gerarSalt();
        String passwordHash = hashSenhaComSalt(data.senha(), salt);

        // Define o hash e o salt no objeto User associado ao Funcionario
        user.setPasswordHash(passwordHash);
        user.setPasswordSalt(salt);

        // Cria o Funcionario usando o construtor com FuncionarioRequestDTO e User
        Funcionario newFuncionario = new Funcionario(data, user);
        repository.save(newFuncionario);
        return newFuncionario;
    }

    @Transactional
    public Optional<Funcionario> updateFuncionario(Long id, FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario existingFuncionario = funcionarioOptional.get();
            existingFuncionario.setNome(data.nome());
            existingFuncionario.setEmail(data.email());
            existingFuncionario.setDataNascimento(data.dataNascimento());

            // Gera novo salt e hash para a senha atualizada
            String salt = gerarSalt();
            String passwordHash = hashSenhaComSalt(data.senha(), salt);

            existingFuncionario.getUser().setPasswordHash(passwordHash);
            existingFuncionario.getUser().setPasswordSalt(salt);

            repository.save(existingFuncionario);
            return Optional.of(existingFuncionario);
        }
        return Optional.empty();
    }

    // Gera um salt aleatório
    private String gerarSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash com salt para a senha
    private String hashSenhaComSalt(String senha, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String senhaComSalt = senha + salt;
        byte[] hash = md.digest(senhaComSalt.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @Transactional
    public boolean validarSenha(String senha, Funcionario funcionario) throws NoSuchAlgorithmException {
        String hashSenhaFornecida = hashSenhaComSalt(senha, funcionario.getUser().getPasswordSalt());
        return hashSenhaFornecida.equals(funcionario.getUser().getPasswordHash());
    }
}
