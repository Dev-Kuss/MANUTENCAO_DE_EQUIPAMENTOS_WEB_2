package com.tads.me.controller;

import com.tads.me.entity.Cliente;
import com.tads.me.dto.ClienteRequestDTO;
import com.tads.me.dto.ClienteResponseDTO;
import com.tads.me.repository.ClienteRepository;
import com.tads.me.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cliente")
class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/create")
    public ResponseEntity<Cliente> create(@RequestBody ClienteRequestDTO data) throws NoSuchAlgorithmException {
        // Cria o cliente
        Cliente newCliente = this.clienteService.createCliente(data);

        // Enviar e-mail com a senha
        enviarEmailComSenha(data.email(), data.senha());

        return ResponseEntity.ok(newCliente);
    }

    private void enviarEmailComSenha(String emailDestino, String senha) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailDestino);
            message.setSubject("Sua senha de acesso ao sistema");
            message.setText("Olá,\n\nSeu cadastro foi realizado com sucesso! Sua senha de acesso é: " + senha + "\n\nPor favor, mantenha-a em segurança.");
            // Envia o e-mail
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();  // You can replace this with proper logging if necessary
        }
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

    @GetMapping("/read/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long id) {
        Optional<Cliente> existingItemOptional = repository.findById(id);

        return existingItemOptional.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO data) {
        Optional<Cliente> clienteOptional = this.clienteService.updateCliente(id, data);
        return clienteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

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
