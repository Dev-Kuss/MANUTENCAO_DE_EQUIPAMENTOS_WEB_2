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
import jakarta.persistence.EntityNotFoundException;

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
        userRepository.save(newUser);

        // Criar o funcionário usando o mesmo ID do usuário
        Funcionario newFuncionario = new Funcionario();
        newFuncionario.setId(newUser.getId());  // Usa o mesmo ID do usuário
        newFuncionario.setUser(newUser);
        newFuncionario.setEmail(newUser.getEmail());
        newFuncionario.setNome(data.nome());
        newFuncionario.setTelefone(data.telefone());
        newFuncionario.setDataNascimento(data.dataNascimento());

        return funcionarioRepository.save(newFuncionario);
    }
       
    

    @Transactional
    public Optional<Funcionario> updateFuncionario(UUID id, FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario existingFuncionario = funcionarioOptional.get();
            User existingUser = existingFuncionario.getUser();
            
            // Verify if user exists
            if (existingUser == null) {
                throw new EntityNotFoundException("User not found for funcionario: " + id);
            }
            
            // Check if email is already in use by another user
            if (!existingUser.getEmail().equals(data.email()) && 
                userRepository.existsByEmail(data.email())) {
                throw new IllegalArgumentException("Email já está em uso por outro usuário.");
            }
            
            // Update user information
            existingUser.setNome(data.nome());
            existingUser.setEmail(data.email());
            if (data.senha() != null && !data.senha().isEmpty()) {
                String passwordHashSalt = passwordEncoder.encode(data.senha());
                existingUser.setPasswordHashSalt(passwordHashSalt);
            }
            userRepository.save(existingUser);
            
            // Update funcionario information
            existingFuncionario.setNome(data.nome());
            existingFuncionario.setEmail(data.email());
            existingFuncionario.setTelefone(data.telefone());
            existingFuncionario.setDataNascimento(data.dataNascimento());
            
            return Optional.of(funcionarioRepository.save(existingFuncionario));
        }
        return Optional.empty();
    }

    @Transactional
    public void deleteFuncionario(UUID id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            User user = funcionario.get().getUser();
            if (user != null) {
                funcionarioRepository.deleteById(id);
                userRepository.deleteById(user.getId());
            } else {
                throw new EntityNotFoundException("User not found for funcionario: " + id);
            }
        } else {
            throw new EntityNotFoundException("Funcionario not found with id: " + id);
        }
    }

}
