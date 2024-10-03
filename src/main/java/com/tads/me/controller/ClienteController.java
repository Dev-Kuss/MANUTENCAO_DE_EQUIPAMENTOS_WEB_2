package com.tads.me.controller;

import java.util.List;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tads.me.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
class ClienteController {

    @Autowired
    ClienteRepository repository;

    @PostMapping
    public void create(@RequestBody ClienteRequestDTO data) {
        Cliente clienteData = new Cliente(data);
        repository.save(clienteData);

    }
}

//     @GetMapping("{id}")
//     public ResponseEntity<Cliente> getById(@PathVariable("id") Long id) {
//         Optional<Cliente> existingItemOptional = repository.findById(id);

//         if (existingItemOptional.isPresent()) {
//             return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @PutMapping("{id}")
//     public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody Cliente item) {
//         Optional<Cliente> existingItemOptional = repository.findById(id);
//         if (existingItemOptional.isPresent()) {
//             Cliente existingItem = existingItemOptional.get();
//             System.out.println("TODO for developer - update logic is unique to entity and must be implemented manually.");
//             existingItem.setSomeField(item.getSomeField());
//             return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping("{id}")
//     public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
//         try {
//             repository.deleteById(id);
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         } catch (Exception e) {
//             return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//         }
//     }
// }
