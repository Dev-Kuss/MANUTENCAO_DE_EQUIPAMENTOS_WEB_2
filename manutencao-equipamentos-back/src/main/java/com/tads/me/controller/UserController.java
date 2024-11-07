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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
@Tag(name = "Usuário", description = "Gerenciamento de Usuários")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Cria um novo usuário", description = "Endpoint para criar um novo usuário com as informações fornecidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = this.userService.createUser(userRequestDTO);
        UserResponseDTO userResponseDTO = new UserResponseDTO(newUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(userResponseDTO);
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
