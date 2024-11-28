import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao, HistoricoSolicitacao, Orcamento } from '../../models/solicitacao.model';
import { Funcionario } from '../../models/funcionario.model';
import { Cliente } from '../../models/cliente.model';
import { ClienteService } from '../../services/cliente.service';

@Component({
  selector: 'app-efetuar-manutencao',
  templateUrl: './efetuar-manutencao.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ]
})

export class EfetuarManutencaoComponent implements OnInit {
  
  @Input() solicitacao: Solicitacao | null = null;
  @Input() cliente: Cliente = {} as Cliente;
  @Input() funcionarios: Funcionario[] = [];
  @Input() funcionarioLogado!: Funcionario;

  constructor(private clienteService: ClienteService) {}

  ngOnInit(): void {
    this.getCliente();
  }

  getCliente(): void {
    if (this.solicitacao?.idCliente) {
      this.clienteService.getClienteById(this.solicitacao.idCliente).subscribe({
        next: (data: Cliente) => {
          this.cliente = data;
        },
        error: (err) => {
          console.error('Erro ao obter cliente:', err);
        }
      });
    }
  }

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
        idFuncionario: this.funcionarioLogado.id,
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
          idFuncionario: this.funcionarioLogado.id,
      };
      this.solicitacao.historicos.push(historico);

      console.log('Solicitação redirecionada com sucesso:', this.solicitacao);
    }
  }
}
