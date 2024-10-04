// cliente-home.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  
import { RouterModule } from '@angular/router';  
import { ReactiveFormsModule } from '@angular/forms';  

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { SolicitarManutencaoComponent } from "../../components/solicitar-manutencao-form/solicitar-manutencao.component";
import { MostrarOrcamentoComponent } from '../../components/mostrar-orcamento-form/mostrar-orcamento.component';

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  estado: string;
  precoOrcado?: number;
}

@Component({
  selector: 'app-cliente-home',
  templateUrl: './cliente-home.component.html',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule, BaseModalComponent, SolicitarManutencaoComponent, MostrarOrcamentoComponent]
})

export class ClienteHomeComponent {
  isManutencaoModalOpen = false;
  isOrcamentoModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;
  solicitacoes: Solicitacao[] = [
    {
      dataHora: new Date('2024-09-25T10:30:00'),
      descricaoEquipamento: 'Máquina de Lavar',
      estado: 'ORÇADA'
    },
    {
      dataHora: new Date('2024-09-24T14:45:00'),
      descricaoEquipamento: 'Computador',
      estado: 'APROVADA'
    },
    {
      dataHora: new Date('2024-09-23T09:20:00'),
      descricaoEquipamento: 'Micro-ondas',
      estado: 'REJEITADA'
    },
    {
      dataHora: new Date('2024-09-22T16:00:00'),
      descricaoEquipamento: 'Refrigerador',
      estado: 'ARRUMADA'
    }
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
  
  visualizarSolicitacao(solicitacao: Solicitacao) {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
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
