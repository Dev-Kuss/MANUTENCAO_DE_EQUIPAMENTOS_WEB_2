package com.tads.me.controller;

import com.tads.me.entity.Endereco;
import com.tads.me.dto.EnderecoRequestDTO;
import com.tads.me.dto.EnderecoResponseDTO;
import com.tads.me.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> createEndereco(@RequestBody EnderecoRequestDTO data) {
        Endereco newEndereco = service.createEndereco(data);
        return ResponseEntity.ok(new EnderecoResponseDTO(newEndereco));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> getEnderecoById(@PathVariable Long id) {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        return endereco.map(e -> ResponseEntity.ok(new EnderecoResponseDTO(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
