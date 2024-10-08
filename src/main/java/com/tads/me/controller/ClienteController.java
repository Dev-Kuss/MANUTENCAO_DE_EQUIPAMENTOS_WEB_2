package com.tads.me.controller;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.repositories.ClienteRepository;
import com.tads.me.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
class ClienteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        try {
            List<Cliente> items = new ArrayList<Cliente>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteRequestDTO data) {
        Cliente newCliente = this.clienteService.createCliente(data);
        return ResponseEntity.ok(newCliente);
    }
}