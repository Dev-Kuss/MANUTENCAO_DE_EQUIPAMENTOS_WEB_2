import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao, HistoricoSolicitacao } from '../../models/solicitacao.model';

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

      const historico: HistoricoSolicitacao = {
        dataHora: new Date(),
        descricao: `Solicitação finalizada por ${this.funcionarioLogado}`,
        funcionario: {
          idFuncionario: 1, // Ajuste conforme necessário
          nome: this.funcionarioLogado
        }
      };
      this.solicitacao.historicos?.push(historico);
      this.solicitacao.dataHoraFinalizacao = new Date();

      console.log('Solicitação finalizada com sucesso:', this.solicitacao);
    }
  }
}
