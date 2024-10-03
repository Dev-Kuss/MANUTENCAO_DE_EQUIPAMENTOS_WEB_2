// cliente-home.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  
import { RouterModule } from '@angular/router';  
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';  

import { ModalComponent } from "../../components/modal/modal.component";
import { SolicitacaoOrcamentoModalComponent } from '../../components/solicitacao-orcamento-modal/solicitacao-orcamento-modal.component';
import { DrawerService } from '../../services/modal.service';
import { SolicitacaoService } from '../../services/solicitacao-orcamento-modal.service';

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
  imports: [CommonModule, RouterModule, ReactiveFormsModule, ModalComponent, SolicitacaoOrcamentoModalComponent]
})

export class ClienteHomeComponent {
  isDrawerOpen: boolean = false;
  solicitacaoSelecionada: Solicitacao | null = null;
  mostrarModal = false;

  categorias = ['Notebook', 'Desktop', 'Impressora', 'Mouse', 'Teclado']; 
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
  
  constructor(private drawerService: DrawerService, private solicitacaoService: SolicitacaoService) {}
  
  abrirDrawer() {
    this.drawerService.openDrawer();
  }
  
  visualizarSolicitacao(solicitacao: Solicitacao) {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
    // TODO: Navegar para a página de visualização da solicitação (RF008)
  }

  aprovarRejeitarServico() {
    console.log('Aprovar ou Rejeitar Serviço');
    // TODO: Implementar lógica para RF005
  }

  resgatarServico() {
    console.log('Resgatar Serviço');
    // TODO: Implementar lógica para RF009
  }

  pagarServico() {
    console.log('Pagar Serviço');
    // TODO: Implementar lógica para RF010
  }

  abrirModalSolicitacao(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.mostrarModal = true;
  }

  fecharModal() {
    this.mostrarModal = false;
  }

  aprovarServico() {
    if (this.solicitacaoSelecionada) {
      this.solicitacaoService.aprovarSolicitacao(this.solicitacaoSelecionada);
      this.fecharModal();
    }
  }

  rejeitarServico() {
    if (this.solicitacaoSelecionada) {
      this.solicitacaoService.rejeitarSolicitacao(this.solicitacaoSelecionada);
      this.fecharModal();
    }
  }
}
