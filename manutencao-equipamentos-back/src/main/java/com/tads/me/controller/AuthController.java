package com.tads.me.controller;

import com.tads.me.dto.LoginRequestDTO;
import com.tads.me.dto.LoginResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.security.JwtTokenProvider;
import com.tads.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // Autentica o usuário com email e senha
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Busca o usuário autenticado pelo email
            Optional<User> optionalUser = userService.findByEmail(loginRequest.email());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(404).build(); // Retorna 404 se o usuário não for encontrado
            }

            User user = optionalUser.get();  // Obtém o User do Optional

            // Gera o token JWT
            String token = jwtTokenProvider.generateToken(authentication);

            // Converte o Set<String> de roles para List<String>
            List<String> roles = user.getRoles().stream().toList();

            // Cria a resposta com os dados do usuário e o token
            LoginResponseDTO response = new LoginResponseDTO(user.getId(), user.getEmail(), roles, token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build(); // Retorna 401 se a autenticação falhar
        }
    }
}
