// cliente-home.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  
import { RouterModule } from '@angular/router';  
import { ReactiveFormsModule } from '@angular/forms';  

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { SolicitarManutencaoComponent } from "../../components/solicitar-manutencao-form/solicitar-manutencao.component";
import { MostrarOrcamentoComponent } from '../../components/mostrar-orcamento-form/mostrar-orcamento.component';
import { VisualizarServicoComponent } from '../../components/visualizar-servico/visualizar-servico.component';
import { Solicitacao } from '../../models/solicitacao.model';

@Component({
  selector: 'app-cliente-home',
  templateUrl: './cliente-home.component.html',
  standalone: true,
  imports: [
    CommonModule, 
    RouterModule, 
    ReactiveFormsModule, 
    BaseModalComponent, 
    SolicitarManutencaoComponent, 
    MostrarOrcamentoComponent,
    VisualizarServicoComponent
  ]
})

export class ClienteHomeComponent {
  isManutencaoModalOpen = false;
  isOrcamentoModalOpen = false;
  isVisualizarModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;
  solicitacoes: Solicitacao[] = [
    {
      dataHora: new Date('2024-09-25T10:30:00'),
      descricaoEquipamento: 'Máquina de Lavar',
      estado: 'ORÇADA',
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
    console.log(this.isManutencaoModalOpen)
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
  
  visualizarServico(solicitacao: Solicitacao) {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
    this.abrirVisualizarModal(solicitacao)
    // TODO: Navegar para a página de visualização da solicitação (RF008)
  }

  resgatarServico() {
    console.log('Resgatar Serviço');
    // TODO: Implementar lógica para RF009
  }

  pagarServico() {
    console.log('Pagar Serviço');
    // TODO: Implementar lógica para RF010
  }
}
