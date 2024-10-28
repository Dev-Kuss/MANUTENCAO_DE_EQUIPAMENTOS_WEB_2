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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // Autentica o usuário e cria o objeto Authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );

        // Define o contexto de autenticação
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gera o token usando o objeto Authentication
        String token = jwtTokenProvider.generateToken(authentication);

        // Carrega o usuário autenticado e seus dados
        User user = userService.findByEmail(loginRequest.email()).orElseThrow();

        // Cria o LoginResponseDTO com os parâmetros corretos
        LoginResponseDTO response = new LoginResponseDTO(
                user.getId(),                      // UUID do usuário
                user.getEmail(),                   // Email do usuário
                new ArrayList<>(user.getRoles()),  // Converte Set<String> para List<String>
                token                              // Token gerado
        );

        return ResponseEntity.ok(response);
    }
}
