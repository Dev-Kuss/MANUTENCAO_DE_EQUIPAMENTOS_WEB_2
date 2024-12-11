import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faEye,
  faFileInvoiceDollar,
  faUndoAlt,
  faMoneyBillWave,
  faClipboardList,
  faDollarSign,
  faWrench,
  faUserPlus,
  faFilePdf,
  faCheckCircle
} from '@fortawesome/free-solid-svg-icons';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { EfetuarOrcamentoComponent } from '../../components/efetuar-orcamento/efetuar-orcamento.component';
import { EfetuarManutencaoComponent } from '../../components/efetuar-manutencao/efetuar-manutencao.component';
import { FinalizarSolicitacaoComponent } from '../../components/finalizar-solicitacao/finalizar-solicitacao.component';
import { AuthService } from '../../services/auth.service';
import { SolicitacaoService } from '../../services/solicitacao.service';
import { ClienteService } from '../../services/cliente.service';
import { FuncionarioService } from '../../services/funcionario.service';

import { Solicitacao } from '../../models/solicitacao.model';
import { Funcionario } from '../../models/funcionario.model';
import { Cliente } from '../../models/cliente.model';

interface CategoryGroup {
  [key: string]: Solicitacao[];
}

@Component({
  selector: 'app-funcionario-solicitacoes',
  templateUrl: './funcionario-solicitacoes.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BaseModalComponent,
    EfetuarOrcamentoComponent,
    EfetuarManutencaoComponent,
    FinalizarSolicitacaoComponent
  ]
})
export class FuncionarioSolicitacoesComponent implements OnInit {

  funcionarios: Funcionario[] = [];
  paginaAtual: number = 1;
  itensPorPagina: number = 10;

  // Ícones
  faEye = faEye;
  faFileInvoiceDollar = faFileInvoiceDollar;
  faUndoAlt = faUndoAlt;
  faMoneyBillWave = faMoneyBillWave;
  faClipboardList = faClipboardList;
  faDollarSign = faDollarSign;
  faWrench = faWrench;
  faUserPlus = faUserPlus;
  faFilePdf = faFilePdf;
  faCheckCircle = faCheckCircle;

  nomeUsuario: string | null = '';

  isOrcamentoModalOpen = false;
  isManutencaoModalOpen = false;
  isRedirecionarModalOpen = false;
  isFinalizarModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;
  listaFuncionarios: Funcionario[] = [];
  funcionarioLogado: Funcionario | null = null;
  solicitacoes: Solicitacao[] = [];
  clientes: { [id: string]: Cliente } = {};

  estadoCores: any = {
    'ABERTA': 'bg-gray-500',
    'ORÇADA': 'bg-brown-500',
    'REJEITADA': 'bg-red-500',
    'APROVADA': 'bg-yellow-500',
    'REDIRECIONADA': 'bg-purple-500',
    'AGUARDANDO PAGAMENTO': 'bg-blue-500',
    'PAGA': 'bg-orange-500',
    'FINALIZADA': 'bg-green-500'
  };

  filtroSelecionado: string = 'TODAS';
  dataInicio: Date | null = null;
  dataFim: Date | null = null;

  solicitacoesFiltradas: Solicitacao[] = [...this.solicitacoes];

  constructor(
    private authService: AuthService,
    private funcionarioService: FuncionarioService,
    private solicitacaoService: SolicitacaoService,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.loadListaFuncionarios();
    this.loadSolicitacoes();
    this.nomeUsuario = this.authService.getNomeUsuario();
  }

  loadListaFuncionarios(): void {
    this.funcionarioService.getAllFuncionarios().subscribe((funcionarios: Funcionario[]) => {
      this.listaFuncionarios = funcionarios;
    });
  }

  loadSolicitacoes(): void {
    this.solicitacaoService.getSolicitacoes().subscribe({
      next: (solicitacoes) => {
        this.solicitacoes = solicitacoes;
        this.filtrarSolicitacoes();
      },
      error: (error) => {
        console.error('Erro ao carregar solicitações:', error);
      },
    });
  }

  get totalPaginas(): number {
    return Math.ceil(this.solicitacoesFiltradas.length / this.itensPorPagina);
  }

  getPaginasProximas(): number[] {
    const paginas = [];
    const startPage = Math.max(2, this.paginaAtual - 1);
    const endPage = Math.min(this.totalPaginas - 1, this.paginaAtual + 1);

    for (let i = startPage; i <= endPage; i++) {
      paginas.push(i);
    }
    return paginas;
  }

  getDestinoRedirecionamento(solicitacao: Solicitacao): string | undefined {
    const historico = solicitacao.historicos?.find(h => h.destinoRedirecionamento);
    return historico?.destinoRedirecionamento;
  }

  get solicitacoesPaginadas(): Solicitacao[] {
    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    return this.solicitacoesFiltradas.slice(inicio, fim);
  }

  mudarPagina(numeroPagina: number) {
    this.paginaAtual = numeroPagina;
  }

  abrirOrcamentoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isOrcamentoModalOpen = true;
  }

  fecharOrcamentoModal() {
    this.isOrcamentoModalOpen = false;
  }

  abrirManutencaoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isManutencaoModalOpen = true;
  }

  fecharManutencaoModal() {
    this.isManutencaoModalOpen = false;
  }

  abrirRedirecionarModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isRedirecionarModalOpen = true;
  }

  fecharRedirecionarModal() {
    this.isRedirecionarModalOpen = false;
  }

  abrirFinalizarModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isFinalizarModalOpen = true;
  }

  fecharFinalizarModal() {
    this.isFinalizarModalOpen = false;
  }

  efetuarOrcamento(solicitacao: Solicitacao) {
    this.abrirOrcamentoModal(solicitacao);
  }

  efetuarManutencao(solicitacao: Solicitacao) {
    this.abrirManutencaoModal(solicitacao);
  }

  redirecionarManutencao(solicitacao: Solicitacao) {
    this.abrirRedirecionarModal(solicitacao);
  }

  finalizarSolicitacao(solicitacao: Solicitacao) {
    this.abrirFinalizarModal(solicitacao);
  }

  atualizarFiltro(filtro: string) {
    this.filtroSelecionado = filtro;
    this.filtrarSolicitacoes();
  }

  filtrarSolicitacoes() {
    const hoje = new Date();
    hoje.setHours(0, 0, 0, 0);

    if (this.filtroSelecionado === 'HOJE') {
      this.solicitacoesFiltradas = this.solicitacoes.filter(solicitacao => {
        const dataSolicitacao = new Date(solicitacao.dataHora);
        dataSolicitacao.setHours(0,0,0,0);
        return dataSolicitacao.getTime() === hoje.getTime();
      });
    } else if (this.filtroSelecionado === 'PERIODO') {
      if (this.dataInicio && this.dataFim) {
        const inicio = new Date(this.dataInicio);
        const fim = new Date(this.dataFim);

        this.solicitacoesFiltradas = this.solicitacoes.filter(solicitacao => {
          const dataSolicitacao = new Date(solicitacao.dataHora);
          return dataSolicitacao.getTime() >= inicio.getTime() && dataSolicitacao.getTime() <= fim.getTime();
        });
      } else {
        this.solicitacoesFiltradas = [];
      }
    } else {
      this.solicitacoesFiltradas = [...this.solicitacoes];
    }
  }

  filtrarSolicitacoesPorData(): Solicitacao[] {
    if (this.dataInicio && this.dataFim) {
      return this.solicitacoes.filter(solicitacao => {
        const dataSolicitacao = solicitacao.dataHora.getTime();
        return dataSolicitacao >= this.dataInicio!.getTime() && dataSolicitacao <= this.dataFim!.getTime();
      });
    }
    return this.solicitacoes;
  }

  gerarRelatorioReceitas() {
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.getWidth();
    doc.text('Relatório de Receitas por Período', pageWidth / 2, 10, { align: 'center' });

    const solicitacoesFiltradas = this.filtrarSolicitacoesPorData();
    const groupedByDate = solicitacoesFiltradas.reduce((acc, curr) => {
      const ultimoOrcamento = curr.orcamentos?.slice(-1)[0];
      if (ultimoOrcamento?.valor !== undefined) {
        const dateKey = curr.dataHora.toISOString().split('T')[0];
        if (!acc[dateKey]) acc[dateKey] = [];
        acc[dateKey].push(curr);
      }
      return acc;
    }, {} as { [key: string]: Solicitacao[] });

    const dataTable: any[] = [];
    for (const [date, solicitacoes] of Object.entries(groupedByDate)) {
      const totalDia = solicitacoes.reduce((sum, s) => {
        const ultimoOrcamento = s.orcamentos?.slice(-1)[0];
        return sum + (ultimoOrcamento?.valor ?? 0);
      }, 0);
      
      solicitacoes.forEach(solicitacao => {
        const ultimoOrcamento = solicitacao.orcamentos?.slice(-1)[0];
        const cliente = this.clientes[solicitacao.idCliente];
        dataTable.push([
          date,
          cliente?.nome ?? 'N/A',
          solicitacao.descricaoEquipamento,
          `R$ ${ultimoOrcamento?.valor ?? 0}`
        ]);
      });
      dataTable.push([`${date} - Total`, '', '', `R$ ${totalDia}`]);
    }

    (doc as any).autoTable({
      head: [['Data', 'Cliente', 'Descrição', 'Valor']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-periodo.pdf');
  }

  gerarRelatorioReceitasPorCategoria() {
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.getWidth();
    doc.text('Relatório de Receitas por Categoria', pageWidth / 2, 10, { align: 'center' });

    const groupedByCategory: CategoryGroup = this.solicitacoes.reduce((acc, curr) => {
      if (curr.orcamentos && curr.orcamentos.length > 0) {
        const categoria = curr.idCategoria;
        if (!acc[categoria]) acc[categoria] = [];
        acc[categoria].push(curr);
      }
      return acc;
    }, {} as CategoryGroup);

    const dataTable: any[] = [];
    for (const [categoria, solicitacoes] of Object.entries(groupedByCategory)) {
      const totalCategoria = solicitacoes.reduce((sum, s) => {
        const ultimoOrcamento = s.orcamentos?.slice(-1)[0];
        return sum + (ultimoOrcamento?.valor ?? 0);
      }, 0);
      solicitacoes.forEach(solicitacao => {
        dataTable.push([
          categoria,
          this.clientes[solicitacao.idCliente]?.nome ?? 'N/A',
          solicitacao.descricaoEquipamento,
          `R$ ${solicitacao.orcamentos?.slice(-1)[0]?.valor ?? 0}`
        ]);
      });
      dataTable.push([`${categoria} - Total`, '', '', `R$ ${totalCategoria}`]);
    }

    (doc as any).autoTable({
      head: [['Categoria', 'Cliente', 'Descrição', 'Valor']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-categoria.pdf');
  }
}
