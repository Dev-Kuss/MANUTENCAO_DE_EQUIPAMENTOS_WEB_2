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
  }

  abrirOrcamentoModal(solicitacao: Solicitacao): void {
    this.solicitacaoSelecionada = solicitacao;
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
    const previousEstado = solicitacao.estado;
    solicitacao.estado = 'APROVADA';

    if (!solicitacao.historicos) {
      solicitacao.historicos = [];
    }

    solicitacao.historicos.push({
      dataHora: new Date(),
      descricao: `Solicitação passou de ${previousEstado} para APROVADA.`,
        idFuncionario: 0, 
    });

    alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
  }

  pagarServico(): void {
    console.log('Pagar Serviço');
    // TODO: Implementar lógica para RF010
  }

  onPagamentoConfirmado(): void {
    this.fecharPagamentoModal();
    // Optional: Additional actions after payment confirmation
  }
}
