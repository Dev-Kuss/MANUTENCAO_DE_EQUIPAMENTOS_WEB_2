package com.tads.me.controller;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.dto.FuncionarioResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/funcionario")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Funcionário", description = "Gerenciamento de Funcionários")
class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioRepository repository;

    @Operation(
            summary = "Cria um novo funcionário",
            description = "Cria um novo funcionário com base nos dados fornecidos, com acesso restrito a administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<Funcionario> create(@RequestBody FuncionarioRequestDTO data, @AuthenticationPrincipal User authenticatedUser) throws NoSuchAlgorithmException {
        Funcionario newFuncionario = this.funcionarioService.createFuncionario(data, authenticatedUser);  // Passar o usuário autenticado
        return ResponseEntity.ok(newFuncionario);
    }

    @Operation(
            summary = "Lista todos os funcionários",
            description = "Recupera uma lista de todos os funcionários, retornando HTTP 204 se estiver vazia. Acesso restrito a administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum funcionário encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<FuncionarioResponseDTO>> getAll() {
        try {
            List<Funcionario> items = repository.findAll();

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<FuncionarioResponseDTO> responseItems = items.stream()
                    .map(FuncionarioResponseDTO::new)
                    .toList();

            return new ResponseEntity<>(responseItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Obtém um funcionário pelo ID",
            description = "Recupera os detalhes de um funcionário específico pelo ID fornecido. Acesso restrito a administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable("id") Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);

        return existingItemOptional.map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Atualiza um funcionário",
            description = "Atualiza as informações de um funcionário existente pelo ID. Acesso restrito a administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado para atualização"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Optional<Funcionario> funcionarioOptional = this.funcionarioService.updateFuncionario(id, data);

        return funcionarioOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(
            summary = "Exclui um funcionário",
            description = "Exclui um funcionário pelo ID fornecido. Acesso restrito a administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Funcionário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado para exclusão"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
