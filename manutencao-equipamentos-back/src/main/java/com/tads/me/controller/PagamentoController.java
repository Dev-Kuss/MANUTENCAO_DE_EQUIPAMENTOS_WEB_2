package com.tads.me.controller;

import com.tads.me.domain.pagamento.Pagamento;
import com.tads.me.domain.pagamento.PagamentoRequestDTO;
import com.tads.me.domain.pagamento.PagamentoResponseDTO;
import com.tads.me.repository.PagamentoRepository;
import com.tads.me.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Pagamento> create(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        try {
            Pagamento pagamento = pagamentoService.createPagamento(pagamentoRequestDTO);
            return ResponseEntity.ok(pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPagamentos() {
        try {
            List<PagamentoResponseDTO> pagamentos = pagamentoService.listarPagamentos();
            if (pagamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(pagamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable("id") Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.getById(id);
        return pagamentoOptional
                .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") Long id, @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        try {
            Optional<Pagamento> updatedPagamento = pagamentoService.updatePagamento(id, pagamentoRequestDTO);
            if (updatedPagamento.isPresent()) {
                return ResponseEntity.ok(updatedPagamento.get());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
