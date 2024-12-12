import { Component, Input, Output, EventEmitter } from '@angular/core';
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
  @Input() fecharModal!: () => void;
  @Output() orcamentoConfirmado = new EventEmitter<void>();
  valorOrcamento!: number;
  descricaoServico!: string;

  constructor(private solicitacaoService: SolicitacaoService) {}

  confirmarOrcamento() {
    if (this.valorOrcamento && this.solicitacao && this.descricaoServico) {
      const novoOrcamento: Orcamento = {
        valor: this.valorOrcamento,
        descricao: this.descricaoServico,
        dataHora: new Date(),
        aprovado: false,
        solicitacaoId: this.solicitacao.idSolicitacao,
        funcionarioId: localStorage.getItem('id') || ''
      };

      this.solicitacaoService.createOrcamento(novoOrcamento).subscribe({
        next: () => {
          this.solicitacaoService.patchSolicitacao(this.solicitacao!.idSolicitacao, { estado: 'ORÇADA' }).subscribe({
            next: () => {
              alert('Orçamento Realizado com Sucesso!');
              this.valorOrcamento = 0;
              this.descricaoServico = '';
              this.orcamentoConfirmado.emit();
              if (this.fecharModal) {
                this.fecharModal();
              }
              window.location.reload();
            },
            error: (error) => {
              console.error('Erro ao atualizar solicitação:', error);
              alert('Erro ao atualizar o estado da solicitação');
            }
          });
        },
        error: (error) => {
          if (error.status === 200) {
            this.solicitacaoService.patchSolicitacao(this.solicitacao!.idSolicitacao, { estado: 'ORÇADA' }).subscribe({
              next: () => {
                alert('Orçamento Realizado com Sucesso!');
                this.valorOrcamento = 0;
                this.descricaoServico = '';
                this.orcamentoConfirmado.emit();
                if (this.fecharModal) {
                  this.fecharModal();
                }
                window.location.reload();
              },
              error: (patchError) => {
                console.error('Erro ao atualizar solicitação:', patchError);
                alert('Erro ao atualizar o estado da solicitação');
              }
            });
          } else {
            console.error('Erro ao registrar orçamento:', error);
            alert('Erro ao registrar orçamento');
          }
        }
      });
    } else {
      alert('Por favor, preencha todos os campos obrigatórios');
    }
  }
}
