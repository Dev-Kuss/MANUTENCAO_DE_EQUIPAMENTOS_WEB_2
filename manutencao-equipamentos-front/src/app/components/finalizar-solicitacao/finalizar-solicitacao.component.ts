import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Funcionario } from '../../models/funcionario.model';
import { Solicitacao } from '../../models/solicitacao.model';
import { SolicitacaoService } from '../../services/solicitacao.service';

@Component({
  selector: 'app-finalizar-solicitacao',
  templateUrl: './finalizar-solicitacao.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class FinalizarSolicitacaoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() funcionarioLogado!: Funcionario;
  @Input() fecharModal!: () => void;
  @Output() solicitacaoFinalizada = new EventEmitter<void>();

  constructor(private solicitacaoService: SolicitacaoService) {}

  finalizar() {
    if (this.solicitacao) {
      const updates = {
        estado: 'FINALIZADA',
        dataHoraFinalizacao: new Date().toISOString().replace(/\.\d{3}Z$/, '')
      };

      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          console.log('Solicitação finalizada com sucesso');
          this.solicitacaoFinalizada.emit();
          alert('Solicitação finalizada com sucesso!');
          if (this.fecharModal) {
            this.fecharModal();
          }
          window.location.reload();
        },
        error: (error) => {
          console.error('Erro ao finalizar solicitação:', error);
          alert('Erro ao finalizar solicitação. Por favor, tente novamente.');
        }
      });
    }
  }
}
