import { CommonModule } from '@angular/common';
import { Component, Input, Output, EventEmitter } from '@angular/core';

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  estado: string;
  precoOrcado?: number;
}

@Component({
  selector: 'app-solicitacao-orcamento-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './solicitacao-orcamento-modal.component.html',
  styleUrls: ['./solicitacao-orcamento-modal.component.css'],
})

export class SolicitacaoOrcamentoModalComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() precoOrcado: number = 0; // Preço orçado

  @Output() onAprovar = new EventEmitter<void>();
  @Output() onRejeitar = new EventEmitter<void>();

  aprovarServico() {
    this.onAprovar.emit();
  }

  rejeitarServico() {
    this.onRejeitar.emit();
  }
}
