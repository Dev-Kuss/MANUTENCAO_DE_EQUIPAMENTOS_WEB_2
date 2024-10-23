import { Component, Input } from '@angular/core';
import { Solicitacao } from '../../models/solicitacao.model'; // Importe correto para o modelo Solicitacao
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

  confirmarOrcamento() {
    console.log(this.solicitacao)

    if (this.valorOrcamento && this.solicitacao) {
      // Aqui registramos o orçamento com o valor fornecido, o funcionário logado, a data e hora
      const orcamento = {
        valor: this.valorOrcamento,
        funcionario: 'Funcionário Logado', // Obtido do contexto de login
        data: new Date(),
        solicitacaoId: this.solicitacao.id
      };

      this.salvarOrcamento(orcamento);
      
      this.solicitacao.estado = 'ORÇADA';

      console.log('Orçamento registrado com sucesso:', orcamento);
    }
  }

  salvarOrcamento(orcamento: any) {
    // Lógica para salvar o orçamento (chamada a um serviço ou API)
  }
}
