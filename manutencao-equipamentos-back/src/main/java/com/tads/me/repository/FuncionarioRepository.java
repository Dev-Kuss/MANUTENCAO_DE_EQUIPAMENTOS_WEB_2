package com.tads.me.repository;

import com.tads.me.entity.Funcionario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
}