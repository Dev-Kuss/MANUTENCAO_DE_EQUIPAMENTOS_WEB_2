import { Component } from '@angular/core';
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

import { Solicitacao } from '../../models/solicitacao.model';

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

export class ClienteHomeComponent {
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
  solicitacoes: Solicitacao[] = [
    {
      dataHora: new Date('2024-09-28T12:00:00'),
      descricaoEquipamento: 'Televisão',
      estado: 'ARRUMADA',
      precoOrcado: 382.0,
      historico: [
        {
          dataHora: new Date('2024-09-28T12:00:00'),
          descricao: 'Solicitação criada pelo cliente.',
          funcionario: 'Cliente',
        },
        {
          dataHora: new Date('2024-09-29T09:00:00'),
          descricao: 'Serviço concluído e aguardando pagamento.',
          funcionario: 'Técnico Ana',
        },
      ],
    },
    {
      dataHora: new Date('2024-09-25T10:30:00'),
      descricaoEquipamento: 'Máquina de Lavar',
      estado: 'ORÇADA',
      precoOrcado: 300.0,
      historico: [
        {
          dataHora: new Date('2024-09-24T14:45:00'),
          descricao: 'Solicitação criada pelo cliente.',
          funcionario: 'Cliente',
        },
      ],
    },
    {
      dataHora: new Date('2024-09-24T14:45:00'),
      descricaoEquipamento: 'Computador',
      estado: 'APROVADA',
      historico: [
        {
          dataHora: new Date('2024-09-25T11:00:00'),
          descricao: 'Diagnóstico realizado.',
          funcionario: 'Técnico Pedro',
        },
        {
          dataHora: new Date('2024-09-26T16:30:00'),
          descricao: 'Reparo em andamento.',
          funcionario: 'Técnico Pedro',
        },
      ],
    },
    {
      dataHora: new Date('2024-09-23T09:20:00'),
      descricaoEquipamento: 'Micro-ondas',
      estado: 'REJEITADA',
      historico: [
        {
          dataHora: new Date('2024-09-25T11:00:00'),
          descricao: 'Diagnóstico realizado.',
          funcionario: 'Técnico Pedro',
        },
      ],
    },
    {
      dataHora: new Date('2024-09-22T16:00:00'),
      descricaoEquipamento: 'Refrigerador',
      estado: 'ARRUMADA',
      precoOrcado: 300.0,
      historico: [
        {
          dataHora: new Date('2024-09-26T16:30:00'),
          descricao: 'Reparo em andamento.',
          funcionario: 'Técnico Pedro',
        },
      ],
    },
    {
      dataHora: new Date('2024-09-24T14:45:00'),
      descricaoEquipamento: 'Computador',
      estado: 'APROVADA',
      historico: [
        {
          dataHora: new Date('2024-09-24T14:45:00'),
          descricao: 'Solicitação criada pelo cliente.',
          funcionario: 'Cliente',
        },
        {
          dataHora: new Date('2024-09-25T11:00:00'),
          descricao: 'Diagnóstico realizado.',
          funcionario: 'Técnico Pedro',
        },
        {
          dataHora: new Date('2024-09-26T16:30:00'),
          descricao: 'Reparo em andamento.',
          funcionario: 'Técnico Pedro',
        },
      ],
    },
  ];
  categorias = ['Notebook', 'Desktop', 'Impressora', 'Mouse', 'Teclado']; 
  
  abrirManutencaoModal() {
    this.isManutencaoModalOpen = true;
  }

  fecharManutencaoModal() {
    this.isManutencaoModalOpen = false;
  }

  abrirOrcamentoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isOrcamentoModalOpen = true;
  }

  fecharOrcamentoModal() {
    this.isOrcamentoModalOpen = false;
  }

  abrirVisualizarModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isVisualizarModalOpen  = true;
  }

  fecharVisualizarModal() {
    this.isVisualizarModalOpen  = false;
  }

  abrirPagamentoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isPagamentoModalOpen = true;
  }

  fecharPagamentoModal() {
    this.isPagamentoModalOpen = false;
  }
  
  visualizarServico(solicitacao: Solicitacao) {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
    this.abrirVisualizarModal(solicitacao)
    // TODO: Navegar para a página de visualização da solicitação (RF008)
  }

  resgatarServico(solicitacao: Solicitacao) {
    const previousEstado = solicitacao.estado
    solicitacao.estado = 'APROVADA'

    if (!solicitacao.historico) {
      solicitacao.historico = []
    }

    solicitacao.historico.push({
      dataHora: new Date(),
      descricao: `Solicitação passou de ${previousEstado} para APROVADA.`,
      funcionario: 'Cliente',
    });

    alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
  }

  pagarServico() {
    console.log('Pagar Serviço');
    // TODO: Implementar lógica para RF010
  }

  onPagamentoConfirmado() {
    this.fecharPagamentoModal();
    // Optional: Additional actions after payment confirmation
  }
}
