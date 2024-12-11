import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
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

export class MostrarOrcamentoComponent implements OnChanges {
  @Input() solicitacao: Solicitacao | null = null;
  

  constructor(
    private mostrarOrcamentoService: MostrarOrcamentoService,
    private router: Router
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['solicitacao'] && changes['solicitacao'].currentValue) {
      console.log('MostrarOrcamento - Solicitação:', this.solicitacao);
      console.log('MostrarOrcamento - Orçamentos:', this.solicitacao?.orcamentos);
      if (this.solicitacao?.orcamentos && this.solicitacao.orcamentos.length > 0) {
        const ultimoOrcamento = this.solicitacao.orcamentos[this.solicitacao.orcamentos.length - 1];
        console.log('MostrarOrcamento - Último orçamento:', ultimoOrcamento);
        console.log('MostrarOrcamento - Descrição do último orçamento:', ultimoOrcamento.descricao);
      }
    }
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

  get ultimoOrcamento() {
    return this.solicitacao?.orcamentos?.[this.solicitacao.orcamentos.length - 1];
  }
}
