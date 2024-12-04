import { Component, Input } from '@angular/core';
import { Solicitacao } from '../../models/solicitacao.model';
import { Orcamento } from '../../models/orcamento.model';
import { SolicitacaoService } from '../../services/solicitacao.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-efetuar-orcamento',
  templateUrl: './efetuar-orcamento.component.html',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule
  ]
})
export class EfetuarOrcamentoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  valorOrcamento: number | null = null;

  constructor(private solicitacaoService: SolicitacaoService) {}

  confirmarOrcamento() {
    if (this.valorOrcamento && this.solicitacao) {
      // Criar um novo orçamento
      const novoOrcamento: Orcamento = {
        idOrcamento: 0,
        valor: this.valorOrcamento,
        descricao: 'Orçamento inicial',
        dataHora: new Date(),
        aprovado: false,
        solicitacaoId: this.solicitacao.idSolicitacao,
        funcionarioId: localStorage.getItem('id') || ''
      };

      // Registrar o orçamento
      this.solicitacaoService.createOrcamento(novoOrcamento).subscribe({
        next: (response) => {
          console.log('Orçamento registrado com sucesso:', response);
        },
        error: (error) => {
          console.error('Erro ao registrar orçamento:', error);
        }
      });

      const updates = { estado: 'ORÇADA' }; 
      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          console.log('Solicitação atualizada parcialmente com sucesso');
        },
        error: (error) => {
          console.error('Erro ao atualizar solicitação parcialmente:', error);
        }
      });
    }
  }
}
