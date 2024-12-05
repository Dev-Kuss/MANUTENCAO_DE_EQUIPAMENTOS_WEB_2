import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
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
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EfetuarOrcamentoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  valorOrcamento!: number;
  descricaoServico!: string;

  constructor(private solicitacaoService: SolicitacaoService) {}

  confirmarOrcamento() {
    if (this.valorOrcamento && this.solicitacao) {
      const novoOrcamento: Orcamento = {
        idOrcamento: 0, // TODO: gerar id incremental
        valor: this.valorOrcamento,
        descricao: this.descricaoServico,
        dataHora: new Date(),
        aprovado: false,
        solicitacaoId: this.solicitacao.idSolicitacao,
        funcionarioId: localStorage.getItem('id') || ''
      };

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
