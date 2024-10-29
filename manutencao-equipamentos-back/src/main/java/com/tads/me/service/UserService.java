package com.tads.me.service;

import com.tads.me.dto.UserRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.UserRepository;
import com.tads.me.security.SHA256PasswordEncoder;
import com.tads.me.util.EnviarEmailComSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.tads.me.util.GerarSenhaAleatoria.gerarSenhaAleatoria;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SHA256PasswordEncoder passwordEncoder;

    @Autowired
    private EnviarEmailComSenha enviarEmailComSenha;

    @Transactional
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public User createUser(UserRequestDTO data) {

        if (emailExists(data.email())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        User newUser = new User();
        newUser.setEmail(data.email());

        String senhaOriginal = data.senha() != null ? data.senha() : gerarSenhaAleatoria();
        String passwordHashSalt = passwordEncoder.encode(senhaOriginal); // Inclui o salt

        newUser.setPasswordHashSalt(passwordHashSalt);
        newUser.setRoles(new HashSet<>(Set.of("ADMIN")));

        enviarEmailComSenha.enviarEmailComSenha("Usuário", data.email(), senhaOriginal);

        return saveUser(newUser);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));

        // Constrói um UserDetails com o email, senha e uma lista vazia de permissões
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHashSalt(),
                new ArrayList<>() // Configure as permissões conforme necessário
        );
    }
}