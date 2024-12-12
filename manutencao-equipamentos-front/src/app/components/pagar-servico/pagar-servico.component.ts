import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Funcionario } from '../../models/funcionario.model';
import { Solicitacao } from '../../models/solicitacao.model';
import { SolicitacaoService } from '../../services/solicitacao.service';

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

  constructor(private solicitacaoService: SolicitacaoService) {}

  getUltimoValorOrcamento(): number {
    if (!this.solicitacao?.orcamentos) return 0;
    if (this.solicitacao.orcamentos.length === 0) return 0;
    
    return this.solicitacao.orcamentos[this.solicitacao.orcamentos.length - 1].valor;
  }

  confirmarPagamento() {
    if (this.solicitacao) {
      const updates = {
        estado: 'PAGA',
        dataPagamento: new Date()
      };

      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          console.log('Pagamento registrado com sucesso');
          this.onPagamentoConfirmado.emit();
          alert('Pagamento realizado com sucesso!');
        },
        error: (error) => {
          console.error('Erro ao registrar pagamento:', error);
          alert('Erro ao processar pagamento. Por favor, tente novamente.');
        }
      });
    }
  }
}
