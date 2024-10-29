package com.tads.me.controller;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.dto.ClienteResponseDTO;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Cliente", description = "Gerenciamento de Clientes")
@RequestMapping("/cliente")
class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository repository;

    @Operation(summary = "Cria um novo cliente", description = "Endpoint público para criar um cliente.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<ClienteResponseDTO> create(@RequestBody ClienteRequestDTO data) throws NoSuchAlgorithmException {
        Cliente newCliente = this.clienteService.createCliente(data);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(newCliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCliente.getId())
                .toUri();

        return ResponseEntity.created(location).body(clienteResponseDTO);
    }

    @Operation(summary = "Lista todos os clientes", description = "Endpoint público para listar todos os clientes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<ClienteResponseDTO>> getAll() {
        try {
            List<Cliente> items = repository.findAll();

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<ClienteResponseDTO> responseItems = items.stream()
                    .map(ClienteResponseDTO::new)
                    .toList();

            return new ResponseEntity<>(responseItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Obtém cliente por ID", description = "Acesso restrito a ADMIN.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable UUID id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um cliente", description = "Acesso restrito a ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO data) {
        Optional<Cliente> clienteOptional = this.clienteService.updateCliente(id, data);
        return clienteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Exclui um cliente", description = "Acesso restrito a ADMIN.")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
