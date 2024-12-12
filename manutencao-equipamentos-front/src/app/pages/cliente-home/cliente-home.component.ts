import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEye, faFileInvoiceDollar, faUndoAlt, faMoneyBillWave } from '@fortawesome/free-solid-svg-icons';

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { SolicitarManutencaoComponent } from "../../components/solicitar-manutencao/solicitar-manutencao.component";
import { MostrarOrcamentoComponent } from '../../components/mostrar-orcamento/mostrar-orcamento.component';
import { VisualizarServicoComponent } from '../../components/visualizar-servico/visualizar-servico.component';
import { PagarServicoComponent } from '../../components/pagar-servico/pagar-servico.component';
import { HistoricoSolicitacaoService } from '../../services/historico-solicitacao.service';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';


import { Solicitacao } from '../../models/solicitacao.model';
import { SolicitacaoService } from '../../services/solicitacao.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-cliente-home',
  templateUrl: './cliente-home.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BaseModalComponent,
    SolicitarManutencaoComponent,
    MostrarOrcamentoComponent,
    VisualizarServicoComponent,
    PagarServicoComponent
  ]
})
export class ClienteHomeComponent implements OnInit {
  
  // Icons
  faEye = faEye;
  faFileInvoiceDollar = faFileInvoiceDollar;
  faUndoAlt = faUndoAlt;
  faMoneyBillWave = faMoneyBillWave;
  faSignOutAlt = faSignOutAlt;


  isManutencaoModalOpen = false;
  isOrcamentoModalOpen = false;
  isVisualizarModalOpen = false;
  isPagamentoModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;
  solicitacoes: Solicitacao[] = [];
  nomeUsuario: string | null = '';

  constructor(
    private authService: AuthService,
    private solicitacaoService: SolicitacaoService
  ) {}

  ngOnInit(): void {
    const usuarioId = this.authService.getId(); 

    this.carregarSolicitacoes(usuarioId);
    this.nomeUsuario = this.authService.getNomeUsuario(); 


  }

  carregarSolicitacoes(usuarioId: string | null): void {
    this.solicitacaoService.getSolicitacoes(usuarioId).subscribe(
      (solicitacoes) => {
        this.solicitacoes = solicitacoes;
      },
      (error) => {
        console.error('Erro ao carregar solicitações:', error);
      }
    );
  }

  abrirManutencaoModal(): void {
    this.isManutencaoModalOpen = true;
  }

  fecharManutencaoModal(): void {
    this.isManutencaoModalOpen = false;
    const usuarioId = this.authService.getId();
    this.carregarSolicitacoes(usuarioId);
  }

  onSolicitacaoCriada(): void {
    alert('Solicitação criada com sucesso!');
    this.fecharManutencaoModal();
  }

  abrirOrcamentoModal(solicitacao: Solicitacao): void {
    this.solicitacaoSelecionada = solicitacao;
    console.log('Solicitação selecionada:', this.solicitacaoSelecionada);
    this.isOrcamentoModalOpen = true;
  }

  fecharOrcamentoModal(): void {
    this.isOrcamentoModalOpen = false;
  }

  abrirVisualizarModal(solicitacao: Solicitacao): void {
    this.solicitacaoSelecionada = solicitacao;
    this.isVisualizarModalOpen = true;
  }

  fecharVisualizarModal(): void {
    this.isVisualizarModalOpen = false;
  }

  abrirPagamentoModal(solicitacao: Solicitacao): void {
    this.solicitacaoSelecionada = solicitacao;
    this.isPagamentoModalOpen = true;
  }

  fecharPagamentoModal(): void {
    this.isPagamentoModalOpen = false;
  }

  visualizarServico(solicitacao: Solicitacao): void {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
    this.abrirVisualizarModal(solicitacao);
  }

  resgatarServico(solicitacao: Solicitacao): void {
    if (solicitacao) {
      const updates = {
        estado: 'APROVADA'
      };

      this.solicitacaoService.patchSolicitacao(solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          console.log('Serviço resgatado com sucesso');
          alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
          const usuarioId = this.authService.getId();
          this.carregarSolicitacoes(usuarioId);
        },
        error: (error) => {
          console.error('Erro ao resgatar serviço:', error);
          alert('Erro ao resgatar serviço. Por favor, tente novamente.');
        }
      });
    }
  }

  pagarServico(): void {
    console.log('Pagar Serviço');
  }

  onPagamentoConfirmado(): void {
    this.fecharPagamentoModal();
  }

  logout() {
    window.location.href = '/login';
  }
}
