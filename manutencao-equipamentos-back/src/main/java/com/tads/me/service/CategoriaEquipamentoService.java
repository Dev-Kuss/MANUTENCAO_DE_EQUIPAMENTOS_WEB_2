package com.tads.me.service;

import com.tads.me.domain.categoriaequipamento.CategoriaEquipamentoRequestDTO;
import com.tads.me.domain.categoriaequipamento.CategoriaEquipamentoResponseDTO;
import com.tads.me.domain.categoriaequipamento.CategoriaEquipamento;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaEquipamentoService {

    @Autowired
    private CategoriaEquipamentoRepository repository;

    @Transactional
    public CategoriaEquipamento createCategoria(CategoriaEquipamentoRequestDTO data) {
        CategoriaEquipamento newCategoria = new CategoriaEquipamento();
        newCategoria.setNome_categoria(data.getNome_categoria());
        repository.save(newCategoria);
        return newCategoria;
    }

    @Transactional
    public Optional<CategoriaEquipamento> updateCategoria(Long id, CategoriaEquipamentoRequestDTO data) {
        Optional<CategoriaEquipamento> categoriaOptional = repository.findById(id);
        if (categoriaOptional.isPresent()) {
            CategoriaEquipamento existingCategoria = categoriaOptional.get();
            existingCategoria.setNome_categoria(data.getNome_categoria());
            repository.save(existingCategoria);
            return Optional.of(existingCategoria);
        }
        return Optional.empty();
    }

    public List<CategoriaEquipamentoResponseDTO> listarCategorias() {
        List<CategoriaEquipamento> categorias = repository.findAll();
        return categorias.stream()
                .map(CategoriaEquipamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaEquipamento> getById(Long id) {
        return repository.findById(id);
    }
}
