package com.tads.me.controller;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.dto.FuncionarioResponseDTO;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Funcionário", description = "Gerenciamento de Funcionários")
@RequestMapping("/funcionario")
class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioRepository repository;

    @Operation(summary = "Cria um novo funcionário", description = "Endpoint público para criar um funcionário.")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<FuncionarioResponseDTO> create(@RequestBody FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Funcionario newFuncionario = this.funcionarioService.createFuncionario(data);
        FuncionarioResponseDTO funcionarioResponseDTO = new FuncionarioResponseDTO(newFuncionario);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFuncionario.getId())
                .toUri();

        return ResponseEntity.created(location).body(funcionarioResponseDTO);
    }

    @Operation(summary = "Lista todos os funcionários", description = "Endpoint público para listar todos os funcionários.")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtém um funcionário pelo ID", description = "Recupera os detalhes de um funcionário específico pelo ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<FuncionarioResponseDTO> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(funcionario -> ResponseEntity.ok(new FuncionarioResponseDTO(funcionario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um funcionário", description = "Acesso restrito a ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable UUID id, @RequestBody FuncionarioRequestDTO data) {
        try {
            Optional<Funcionario> funcionarioOptional = this.funcionarioService.updateFuncionario(id, data);
            return funcionarioOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Exclui um funcionário", description = "Acesso restrito a ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Funcionário excluído com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable UUID id) {
        try {
            funcionarioService.deleteFuncionario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // } catch (EntityNotFoundException e) {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
