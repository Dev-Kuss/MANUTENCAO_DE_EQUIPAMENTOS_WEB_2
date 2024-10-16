package com.tads.me.repository;


import com.tads.me.entity.CategoriaEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEquipamentoRepository extends JpaRepository<CategoriaEquipamento, Long> {
}

