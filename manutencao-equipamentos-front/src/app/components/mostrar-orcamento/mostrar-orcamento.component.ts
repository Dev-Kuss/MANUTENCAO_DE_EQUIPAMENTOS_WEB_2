import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { BaseModalComponent } from '../base-modal/base-modal.component';
import { MostrarOrcamentoService } from '../../services/mostrar-orcamento.service';
import { Solicitacao } from '../../models/solicitacao.model';


@Component({
  selector: 'app-mostrar-orcamento',
  standalone: true,
  imports: [CommonModule, BaseModalComponent],
  templateUrl: './mostrar-orcamento.component.html',
})

export class MostrarOrcamentoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  

  constructor(
    private mostrarOrcamentoService: MostrarOrcamentoService,
    private router: Router
  ) { }

  ngOnInit(): void {

    
  }

  aprovarServico() {
    if (this.solicitacao && this.solicitacao.orcamentos?.[0]?.valor !== undefined) {
      this.solicitacao.estado = 'ARRUMADA';

      console.log('Serviço aprovado:', this.solicitacao);

      alert(`Serviço Aprovado no Valor R$ ${this.solicitacao.orcamentos[0].valor.toFixed(2)}`);
    }
  }

  rejeitarServico() {
    if (this.solicitacao) {
      this.solicitacao.estado = 'REJEITADA';
      console.log('Serviço rejeitado:', this.solicitacao);
      alert('Serviço Rejeitado.');
    }
  }
}
