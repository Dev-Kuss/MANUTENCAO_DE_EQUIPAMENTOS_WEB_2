package com.tads.me.controller;

import com.tads.me.security.JwtTokenProvider;  // Certifique-se de que essa classe exista e esteja importada corretamente.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticação do usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),  // Usando o getter gerado automaticamente pelo record
                            loginRequest.password()   // Usando o getter gerado automaticamente pelo record
                    )
            );

            // Gerar o token JWT após autenticação bem-sucedida
            String token = jwtTokenProvider.generateToken(authentication);

            // Retorna o token em um ResponseEntity com status 200 (OK)
            return ResponseEntity.ok(new LoginResponse(token));

        } catch (AuthenticationException e) {
            // Retorna uma resposta 401 Unauthorized em caso de falha na autenticação
            return ResponseEntity.status(401).body("Falha na autenticação: " + e.getMessage());
        }
    }

    // Record para representar a requisição de login
    public record LoginRequest(String username, String password) {}

    // Classe para encapsular a resposta do login (com o token JWT)
    public record LoginResponse(String token) {}
}
