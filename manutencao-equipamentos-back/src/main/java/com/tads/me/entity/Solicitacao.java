package com.tads.me.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false, length = 30)
    private String descricaoEquipamento;

    @Column(length = 255)
    private String descricaoDefeito;

    @Column(length = 255, nullable = true)
    private String descricaoManutencao;

    @Column(length = 255)
    private String orientacoesCliente;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column
    private LocalDateTime dataPagamento;

    @Column
    private LocalDateTime dataHoraFinalizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEquipamento categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_responsavel")
    private Funcionario responsavel;

    @OneToMany(mappedBy = "solicitacao", fetch = FetchType.LAZY)
    @Builder.Default
    private List<HistoricoSolicitacao> historicos = new ArrayList<>(); 

    @OneToMany(mappedBy = "solicitacao", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Orcamento> orcamentos = new ArrayList<>(); 
}
