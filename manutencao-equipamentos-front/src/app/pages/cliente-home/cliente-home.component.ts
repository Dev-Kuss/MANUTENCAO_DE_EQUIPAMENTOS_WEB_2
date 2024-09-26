// cliente-home.component.ts

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  
import { RouterModule } from '@angular/router';  
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';  

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  estado: string;
}

@Component({
  selector: 'app-cliente-home',
  templateUrl: './cliente-home.component.html',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule]
})

export class ClienteHomeComponent {
  solicitacaoForm: FormGroup;
  isDrawerOpen: boolean = false;
  
  constructor(private fb: FormBuilder) {
    this.solicitacaoForm = this.fb.group({
      descricaoEquipamento: ['', [Validators.required]],
      categoriaEquipamento: ['', [Validators.required]],
      descricaoDefeito: ['', [Validators.required]],
    });
  }
  
  categorias = ['Eletrônico', 'Eletrodoméstico', 'Mecânico', 'Outro']; 
  
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

  abrirDrawer() {
    this.isDrawerOpen = true;
  }

  fecharDrawer() {
    this.isDrawerOpen = false;
    this.solicitacaoForm.reset(); // Limpar o formulário ao fechar
  }

  registrarSolicitacao() {
    if (this.solicitacaoForm.valid) {
      const novaSolicitacao: Solicitacao = {
        dataHora: new Date(),
        descricaoEquipamento: this.solicitacaoForm.value.descricaoEquipamento,
        estado: 'ABERTA'
      };
      this.solicitacoes.push(novaSolicitacao); // Adiciona a nova solicitação à lista
      console.log('Nova solicitação registrada:', novaSolicitacao);
      this.fecharDrawer(); // Fecha o drawer após registrar
    } else {
      console.log('Formulário inválido');
    }
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
}
