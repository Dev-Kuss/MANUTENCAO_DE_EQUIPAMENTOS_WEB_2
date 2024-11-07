import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao, HistoricoSolicitacao, Orcamento } from '../../models/solicitacao.model';
import { Funcionario } from '../../models/funcionario.model';

@Component({
  selector: 'app-efetuar-manutencao',
  templateUrl: './efetuar-manutencao.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ]
})

export class EfetuarManutencaoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() funcionarios: Funcionario[] = [];
  @Input() funcionarioLogado!: Funcionario;

  descricaoManutencao: string = '';
  orientacoesCliente: string = '';
  funcionarioDestino: Funcionario | null = null;

  // Variável para controlar a aba selecionada
  abaSelecionada: string = 'manutencao'; // 'manutencao' ou 'redirecionar'

  confirmarManutencao() {
    if (this.solicitacao && this.descricaoManutencao && this.funcionarioLogado) {
      // Atualiza o estado da solicitação para "AGUARDANDO PAGAMENTO"
      this.solicitacao.estado = 'AGUARDANDO PAGAMENTO';

      // Inicializa o array de históricos se não existir
      if (!this.solicitacao.historicos) {
        this.solicitacao.historicos = [];
      }

      // Adiciona o histórico da manutenção
      const historico: HistoricoSolicitacao = {
        dataHora: new Date(),
        descricao: `Manutenção realizada: ${this.descricaoManutencao}. Orientações: ${this.orientacoesCliente}`,
        funcionario: {
          idFuncionario: this.funcionarioLogado.id,
          nome: this.funcionarioLogado.nome
        }
      };
      this.solicitacao.historicos.push(historico);

      console.log('Manutenção registrada com sucesso:', this.solicitacao);
    }
  }

  confirmarRedirecionamento() {
    if (this.solicitacao && this.funcionarioDestino && this.funcionarioLogado) {
      // Atualiza o estado da solicitação para REDIRECIONADA
      this.solicitacao.estado = 'REDIRECIONADA';

      // Inicializa o array de históricos se não existir
      if (!this.solicitacao.historicos) {
        this.solicitacao.historicos = [];
      }

      // Adiciona o histórico do redirecionamento
      const historico: HistoricoSolicitacao = {
        dataHora: new Date(),
        descricao: `Solicitação redirecionada de ${this.funcionarioLogado.nome} para ${this.funcionarioDestino.nome}`,
        funcionario: {
          idFuncionario: this.funcionarioLogado.id,
          nome: this.funcionarioLogado.nome
        }
      };
      this.solicitacao.historicos.push(historico);

      console.log('Solicitação redirecionada com sucesso:', this.solicitacao);
    }
  }
}
