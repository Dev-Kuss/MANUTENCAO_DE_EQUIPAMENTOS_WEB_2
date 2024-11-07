import { Component, Input } from '@angular/core';
import { Solicitacao } from '../../models/solicitacao.model';
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
    console.log(this.solicitacao)

    if (this.valorOrcamento && this.solicitacao) {
        const novoOrcamento = {
            valor: this.valorOrcamento,
            descricao: 'Orçamento inicial',
            dataHora: new Date(),
            aprovado: false
        };

        if (!this.solicitacao.orcamentos) {
            this.solicitacao.orcamentos = [];
        }
        
        this.solicitacao.orcamentos.push(novoOrcamento);
        this.solicitacao.estado = 'ORÇADA';

        console.log('Orçamento registrado com sucesso:', novoOrcamento);
        this.salvarOrcamento(novoOrcamento);
    }
  }

  salvarOrcamento(orcamento: any) {
    if (this.solicitacao && this.solicitacao.idSolicitacao) {
      this.solicitacaoService.updateSolicitacao(this.solicitacao.idSolicitacao, this.solicitacao).subscribe({
        next: (response) => {
          console.log('Orçamento registrado com sucesso:', response);
        },
        error: (error) => {
          console.error('Erro ao registrar orçamento:', error);
        }
      });
    }
  }
}
