package com.tads.me.controller;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.dto.ClienteResponseDTO;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.service.ClienteService;
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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cliente")
class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository repository;

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

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")  // Protegendo com base nas roles
    @GetMapping("/read/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable UUID id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO data) {
        Optional<Cliente> clienteOptional = this.clienteService.updateCliente(id, data);
        return clienteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
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
