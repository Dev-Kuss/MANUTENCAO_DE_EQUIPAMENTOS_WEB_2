package com.tads.me.service;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.repository.UserRepository;
import com.tads.me.security.SHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;


import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.tads.me.util.GerarSenhaAleatoria.gerarSenhaAleatoria;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SHA256PasswordEncoder passwordEncoder;

    @Transactional
    public Funcionario createFuncionario(FuncionarioRequestDTO data, User user) throws NoSuchAlgorithmException {

        String senhaOriginal = data.senha() != null ? data.senha() : gerarSenhaAleatoria();
        String passwordHashSalt = passwordEncoder.encode(senhaOriginal); // Inclui o salt

        User newUser = new User();
        newUser.setNome(data.nome());
        newUser.setPasswordHashSalt(passwordHashSalt);
        newUser.setRoles(new HashSet<>(Set.of("EMPLOYEE")));
        userRepository.save(newUser);

        // Cria o Funcionario usando o construtor com FuncionarioRequestDTO e User
        Funcionario newFuncionario = new Funcionario(data, user);
        funcionarioRepository.save(newFuncionario);
        return newFuncionario;
    }

    @Transactional
    public Optional<Funcionario> updateFuncionario(UUID id, FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario existingFuncionario = funcionarioOptional.get();
            existingFuncionario.setNome(data.nome());
            existingFuncionario.setEmail(data.email());
            existingFuncionario.setDataNascimento(data.dataNascimento());

            String senhaOriginal = data.senha() != null ? data.senha() : gerarSenhaAleatoria();
            String passwordHashSalt = passwordEncoder.encode(senhaOriginal);
            existingFuncionario.getUser().setPasswordHashSalt(passwordHashSalt);

            funcionarioRepository.save(existingFuncionario);
            return Optional.of(existingFuncionario);
        }
        return Optional.empty();
    }

}
