import { Component, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
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
  @Input() fecharModal!: () => void;
  @Input() solicitacao: Solicitacao | null = null;
  @Input() precoOrcado: number = 0;

  constructor(private mostrarOrcamentoService: MostrarOrcamentoService) {}

  aprovarServico() {
    if (this.solicitacao) {
      this.mostrarOrcamentoService.aprovarSolicitacao(this.solicitacao);
      this.fecharModal();
    }
  }

  rejeitarServico() {
    if (this.solicitacao) {
      this.mostrarOrcamentoService.rejeitarSolicitacao(this.solicitacao);
      this.fecharModal();
    }
  }
}
