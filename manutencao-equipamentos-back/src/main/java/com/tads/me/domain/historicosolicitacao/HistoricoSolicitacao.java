package com.tads.me.domain.historicosolicitacao;

import com.tads.me.domain.solicitacao.Solicitacao;
import com.tads.me.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_solicitacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private Solicitacao solicitacao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 50)
    private String estadoAnterior;

    @Column(nullable = false, length = 50)
    private String estadoAtual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", nullable = false)
    private Funcionario funcionario;
}
