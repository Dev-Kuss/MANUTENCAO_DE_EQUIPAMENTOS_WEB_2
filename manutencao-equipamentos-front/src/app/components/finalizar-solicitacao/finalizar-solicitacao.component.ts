import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao, Historico } from '../../models/solicitacao.model';

@Component({
  selector: 'app-finalizar-solicitacao',
  templateUrl: './finalizar-solicitacao.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class FinalizarSolicitacaoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() funcionarioLogado: string = '';

  finalizar() {
    if (this.solicitacao) {
      this.solicitacao.estado = 'FINALIZADA';

      const historico: Historico = {
        dataHora: new Date(),
        descricao: `Solicitação finalizada por ${this.funcionarioLogado}`,
        funcionario: this.funcionarioLogado
      };
      this.solicitacao.historico?.push(historico);
      this.solicitacao.dataHoraFinalizacao = new Date();

      console.log('Solicitação finalizada com sucesso:', this.solicitacao);
    }
  }
}
