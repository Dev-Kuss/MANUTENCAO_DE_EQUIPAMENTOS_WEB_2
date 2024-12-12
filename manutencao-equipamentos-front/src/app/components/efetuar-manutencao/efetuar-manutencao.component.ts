import { Component, Input, Output, OnInit } from '@angular/core';
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
  @Input() fecharModal!: () => void;
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

  abaSelecionada: string = 'manutencao'; 

  confirmarManutencao() {
    if (this.solicitacao && this.descricaoManutencao && this.funcionarioLogado) {
      const updates = {
        estado: 'AGUARDANDO PAGAMENTO',
        idResponsavel: this.funcionarioLogado.id,
        orientacoesCliente: this.orientacoesCliente,
        descricaoManutencao: this.descricaoManutencao
      }; 

      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          alert('Manutenção registrada com sucesso!');
          if (this.fecharModal) {
            this.fecharModal();
          }
          window.location.reload();
        },
        error: (error) => {
          console.error('Erro ao atualizar solicitação parcialmente:', error);
          alert('Erro ao registrar manutenção');
        }
      });
    }
  }

  confirmarRedirecionamento() {
    if (this.solicitacao && this.funcionarioDestino && this.funcionarioLogado) {
      const updates = { estado: 'REDIRECIONADA', idResponsavel: this.funcionarioDestino.id }; 
      
      this.solicitacaoService.patchSolicitacao(this.solicitacao.idSolicitacao, updates).subscribe({
        next: () => {
          alert('Solicitação redirecionada com sucesso!');
          if (this.fecharModal) {
            this.fecharModal();
          }
          window.location.reload();
        },
        error: (error) => {
          console.error('Erro ao atualizar solicitação parcialmente:', error);
          alert('Erro ao redirecionar solicitação');
        }
      });
    }
  }

  get ultimoOrcamento() {
    return this.solicitacao?.orcamentos?.[this.solicitacao?.orcamentos?.length - 1];
  }
}
