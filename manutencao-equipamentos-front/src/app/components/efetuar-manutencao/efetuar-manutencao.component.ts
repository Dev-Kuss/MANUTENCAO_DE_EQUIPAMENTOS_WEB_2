import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao, Historico } from '../../models/solicitacao.model';

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
  @Input() funcionarios: string[] = []; // Lista de funcionários disponível
  @Input() funcionarioLogado: string = ''; // Funcionário logado no sistema

  descricaoManutencao: string = '';
  orientacoesCliente: string = '';
  funcionarioDestino: string | null = null;

  // Variável para controlar a aba selecionada
  abaSelecionada: string = 'manutencao'; // 'manutencao' ou 'redirecionar'

  confirmarManutencao() {
    if (this.solicitacao && this.descricaoManutencao) {
      // Atualiza o estado da solicitação para "AGUARDANDO PAGAMENTO"
      this.solicitacao.estado = 'AGUARDANDO PAGAMENTO';

      // Adiciona o histórico da manutenção
      const historico: Historico = {
        dataHora: new Date(),
        descricao: `Manutenção realizada: ${this.descricaoManutencao}. Orientações: ${this.orientacoesCliente}`,
        funcionario: this.funcionarioLogado
      };
      this.solicitacao.historico?.push(historico);

      console.log('Manutenção registrada com sucesso:', this.solicitacao);
    }
  }

  confirmarRedirecionamento() {
    if (this.solicitacao && this.funcionarioDestino && this.funcionarioDestino !== this.funcionarioLogado) {
      // Atualiza o estado da solicitação para REDIRECIONADA
      this.solicitacao.estado = 'REDIRECIONADA';

      // Adiciona o histórico do redirecionamento
      const historico: Historico = {
        dataHora: new Date(),
        descricao: `Solicitação redirecionada de ${this.funcionarioLogado} para ${this.funcionarioDestino}`,
        funcionario: this.funcionarioLogado
      };
      this.solicitacao.historico?.push(historico);

      console.log('Solicitação redirecionada com sucesso:', this.solicitacao);
    }
  }
}
