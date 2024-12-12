import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Funcionario } from '../../models/funcionario.model';
import { Solicitacao } from '../../models/solicitacao.model';

@Component({
  selector: 'app-pagar-servico',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pagar-servico.component.html',
})
export class PagarServicoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() funcionarioLogado!: Funcionario;
  @Output() onPagamentoConfirmado = new EventEmitter<void>();

  getUltimoValorOrcamento(): number {
    if (!this.solicitacao?.orcamentos) return 0;
    if (this.solicitacao.orcamentos.length === 0) return 0;
    
    return this.solicitacao.orcamentos[this.solicitacao.orcamentos.length - 1].valor;
  }

  confirmarPagamento() {
    if (this.solicitacao) {
      this.solicitacao.estado = 'APROVADA';
      this.solicitacao.dataPagamento = new Date();

      if (!this.solicitacao.historicos) {
        this.solicitacao.historicos = [];
      }

      this.solicitacao.historicos.push({
        dataHora: this.solicitacao.dataPagamento,
        descricao: 'Pagamento confirmado pelo cliente.',
        idFuncionario: this.funcionarioLogado.id,
        nomeFuncionario: this.funcionarioLogado.nome
      });

      // Emit an event to notify parent component
      this.onPagamentoConfirmado.emit();

      // Optional: Display a confirmation message
      alert('Pagamento realizado com sucesso!');
    }
  }
}
