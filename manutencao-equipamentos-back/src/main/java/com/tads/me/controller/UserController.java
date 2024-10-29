package com.tads.me.controller;

import com.tads.me.dto.UserRequestDTO;
import com.tads.me.dto.UserResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Usuário", description = "Gerenciamento de Usuários")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Cria um novo usuário", description = "Endpoint para criar um novo usuário com as informações fornecidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.email());
        user.setPasswordHashSalt(passwordEncoder.encode(userRequestDTO.passwordHashSalt()));

        System.out.println("Password encoder in use: " + passwordEncoder.getClass().getName());

        user.setRoles(userRequestDTO.roles());

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserResponseDTO(savedUser));
    }

    @Operation(summary = "Encontra usuário por email", description = "Busca um usuário pelo seu endereço de email.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/find/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(value -> ResponseEntity.ok(new UserResponseDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }
}
