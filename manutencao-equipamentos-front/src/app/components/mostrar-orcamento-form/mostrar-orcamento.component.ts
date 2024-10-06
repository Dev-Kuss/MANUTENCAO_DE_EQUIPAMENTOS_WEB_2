import { Component, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { BaseModalComponent } from '../base-modal/base-modal.component';
import { MostrarOrcamentoService } from '../../services/mostrar-orcamento.service';

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  estado: string;
  precoOrcado?: number;
}

@Component({
  selector: 'app-mostrar-orcamento',
  standalone: true,
  imports: [CommonModule, BaseModalComponent],
  templateUrl: './mostrar-orcamento.component.html',
})

export class MostrarOrcamentoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  precoOrcado: number | undefined;

  constructor(
    private mostrarOrcamentoService: MostrarOrcamentoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (this.solicitacao) {
      this.precoOrcado = this.solicitacao.precoOrcado;
    }
  }

  aprovarServico() {
    if (this.solicitacao) {
      this.mostrarOrcamentoService.aprovarSolicitacao(this.solicitacao);

      if(this.precoOrcado) {
        alert(`Serviço Aprovado no Valor R$ ${this.precoOrcado.toFixed(2)}`);
      }

      this.router.navigate(['/cliente-home']).then(() => {
        window.location.reload();
      });
    }
  }

  rejeitarServico() {
    if (this.solicitacao) {
      this.mostrarOrcamentoService.rejeitarSolicitacao(this.solicitacao);
      
      alert('Serviço Rejeitado.');

      this.router.navigate(['/cliente-home']).then(() => {
        window.location.reload(); 
      });
    }
  }
}
