package com.tads.me.controller;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
class ClienteController {

    @Autowired
    ClienteService clienteService;
//
//    @PostMapping
//    public void create(@RequestBody ClienteRequestDTO data) {
//        Cliente clienteData = new Cliente(data);
//        repository.sa
////    }ve(clienteData);
//

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody ClienteRequestDTO data) {
        Cliente newCliente = this.clienteService.createCliente(data);
        return ResponseEntity.ok(newCliente);
    }
}