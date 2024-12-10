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
    public Funcionario createFuncionario(FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        // Verificar se o e-mail já está cadastrado
        if (userRepository.existsByEmail(data.email())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        // Criar o usuário
        User newUser = new User();
        newUser.setNome(data.nome());
        newUser.setEmail(data.email());

        // Gerar a senha do usuário
        String senhaOriginal = data.senha() != null ? data.senha() : gerarSenhaAleatoria();
        String passwordHashSalt = passwordEncoder.encode(senhaOriginal);
        newUser.setPasswordHashSalt(passwordHashSalt);
        newUser.setRoles(new HashSet<>(Set.of("EMPLOYEE")));

        // Salvar o usuário no banco
        userRepository.save(newUser);

        // Criar o funcionário associado ao usuário
        Funcionario newFuncionario = new Funcionario();
        newFuncionario.setUser(newUser);
        newFuncionario.setNome(data.nome());
        newFuncionario.setTelefone(data.telefone());
        newFuncionario.setDataNascimento(data.dataNascimento());

        // Salvar o funcionário no banco
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
