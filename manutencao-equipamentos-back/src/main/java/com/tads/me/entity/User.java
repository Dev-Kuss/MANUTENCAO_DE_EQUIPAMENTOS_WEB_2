package com.tads.me.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String passwordHashSalt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cliente cliente;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Funcionario funcionario;
}