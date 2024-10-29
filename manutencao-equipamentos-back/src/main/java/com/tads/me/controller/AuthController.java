package com.tads.me.controller;

import com.tads.me.dto.LoginRequestDTO;
import com.tads.me.dto.LoginResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.security.JwtTokenProvider;
import com.tads.me.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login e Autenticação", description = "Gerenciamento de Login e Autenticação")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(
            summary = "Realiza login de um usuário",
            description = "Autentica o usuário com base no email e senha fornecidos e gera um token JWT em caso de sucesso."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso e token JWT gerado"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas, autenticação falhou"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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
