import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Solicitacao } from '../../models/solicitacao.model';
import { HistoricoSolicitacao } from "../../models/historico-solicitacao.model";
import { Orcamento } from "../../models/orcamento.model";
import { Funcionario } from '../../models/funcionario.model';
import { Cliente } from '../../models/cliente.model';
import { ClienteService } from '../../services/cliente.service';
import { FuncionarioService } from '../../services/funcionario.service';
import { SolicitacaoService } from '../../services/solicitacao.service';


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
  @Input() orcamento: Orcamento[] = [];
  funcionarioLogado: Funcionario | null = null;

  constructor(private clienteService: ClienteService, private solicitacaoService: SolicitacaoService, private funcionarioService: FuncionarioService) {}

  ngOnInit(): void {
    this.getCliente();
    this.getFuncionarioLogado();
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

  getFuncionarioLogado(): void {
    const funcionarioId = localStorage.getItem('id');
    if (funcionarioId) {
      this.funcionarioService.getFuncionarioById(funcionarioId).subscribe({
        next: (funcionario: Funcionario) => {
          this.funcionarioLogado = funcionario;
        },
        error: (error) => {
          console.error('Erro ao obter funcionário logado:', error);
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

      const historico: HistoricoSolicitacao = {
        dataHora: new Date(),
        descricao: `Manutenção realizada: ${this.descricaoManutencao}. Orientações: ${this.orientacoesCliente}`,
        idFuncionario: this.funcionarioLogado.id,
        nomeFuncionario: this.funcionarioLogado.nome
      };
      this.solicitacao.historicos.push(historico);

      console.log('Manutenção registrada com sucesso:', this.solicitacao);
    }
  }

  confirmarRedirecionamento() {
    if (this.solicitacao && this.funcionarioDestino && this.funcionarioLogado) {

      const updates = { estado: 'REDIRECIONADA', idResponsavel: this.funcionarioDestino.id }; 
      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          console.log('Solicitação atualizada parcialmente com sucesso');
        },
        error: (error) => {
          console.error('Erro ao atualizar solicitação parcialmente:', error);
        }
      });


      // // Atualiza o estado da solicitação para REDIRECIONADA
      // this.solicitacao.estado = 'REDIRECIONADA';

      // // Inicializa o array de históricos se não existir
      // if (!this.solicitacao.historicos) {
      //   this.solicitacao.historicos = [];
      // }

      // // Adiciona o histórico do redirecionamento
      // const historico: HistoricoSolicitacao = {
      //   dataHora: new Date(),
      //   descricao: `Solicitação redirecionada de ${this.funcionarioLogado.nome} para ${this.funcionarioDestino.nome}`,
      //     idFuncionario: this.funcionarioLogado.id,
      //     nomeFuncionario: this.funcionarioLogado.nome

      // };
      // this.solicitacao.historicos.push(historico);

      // console.log('Solicitação redirecionada com sucesso:', this.solicitacao);
    }
  }

  get ultimoOrcamento() {
    return this.solicitacao?.orcamentos?.[this.solicitacao?.orcamentos?.length - 1];
  }
}
