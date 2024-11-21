import { Component } from '@angular/core';
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

import { Solicitacao } from '../../models/solicitacao.model';
import { solicitacoes } from '../../seeds/solicitacoes-seed';

@Component({
  selector: 'app-funcionario-home',
  templateUrl: './funcionario-home.component.html',
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
export class FuncionarioHomeComponent {
  // Número da página atual
  paginaAtual: number = 1;

  // Número máximo de itens por página
  itensPorPagina: number = 10;

  // Icons
  faEye = faEye;
  faFileInvoiceDollar = faFileInvoiceDollar;
  faUndoAlt = faUndoAlt;
  faMoneyBillWave = faMoneyBillWave;
  faClipboardList = faClipboardList;
  faDollarSign = faDollarSign;
  faWrench = faWrench;
  faUserPlus = faUserPlus;
  faFilePdf = faFilePdf;
  faCheckCircle = faCheckCircle

  // Modals
  isOrcamentoModalOpen = false;
  isManutencaoModalOpen = false;
  isRedirecionarModalOpen = false;
  isFinalizarModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;

  listaFuncionarios: string[] = ['João Silva', 'Maria Santos', 'Pedro Oliveira', 'Carlos Souza'];
  funcionarioLogado = 'João Silva';

  solicitacoes: Solicitacao[] = [...solicitacoes];


  // Colors mapping
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

  get solicitacoesPaginadas(): Solicitacao[] {
    const inicio = (this.paginaAtual - 1) * this.itensPorPagina;
    const fim = inicio + this.itensPorPagina;
    return this.solicitacoesFiltradas.slice(inicio, fim);
  }

  // Método para mudar a página
  mudarPagina(numeroPagina: number) {
    this.paginaAtual = numeroPagina;
  }

  // Verifica se pode avançar para a próxima página
  proximaPagina(): boolean {
    return this.paginaAtual < Math.ceil(this.solicitacoesFiltradas.length / this.itensPorPagina);
  }

  // Verifica se pode voltar para a página anterior
  paginaAnterior(): boolean {
    return this.paginaAtual > 1;
  }

  // Methods to open modals
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

  // Action methods
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
    this.filtrarSolicitacoes();  // Chamando a filtragem após selecionar o filtro
  }

  filtrarSolicitacoes() {
    const hoje = new Date();
    hoje.setHours(0, 0, 0, 0); // Define o início do dia para a comparação

    if (this.filtroSelecionado === 'HOJE') {
      // Filtra as solicitações que ocorreram hoje
      this.solicitacoesFiltradas = this.solicitacoes.filter(solicitacao => {
        const dataSolicitacao = new Date(solicitacao.dataHora);
        dataSolicitacao.setHours(0, 0, 0, 0);
        return dataSolicitacao.getTime() === hoje.getTime();
      });
    } else if (this.filtroSelecionado === 'PERIODO') {
      if (this.dataInicio && this.dataFim) {
        // Apenas filtra se dataInicio e dataFim não forem null
        const inicio = new Date(this.dataInicio);
        const fim = new Date(this.dataFim);

        this.solicitacoesFiltradas = this.solicitacoes.filter(solicitacao => {
          const dataSolicitacao = new Date(solicitacao.dataHora);
          return dataSolicitacao.getTime() >= inicio.getTime() &&
            dataSolicitacao.getTime() <= fim.getTime();
        });
      } else {
        this.solicitacoesFiltradas = []; // Limpa o resultado se não houver período válido
      }
    } else {
      // Se o filtro for 'TODAS', exibe todas as solicitações
      this.solicitacoesFiltradas = [...this.solicitacoes];
    }
  }

  filtrarSolicitacoesPorData(): Solicitacao[] {
    const inicio = this.dataInicio ? new Date(this.dataInicio).setHours(0, 0, 0, 0) : null;
    const fim = this.dataFim ? new Date(this.dataFim).setHours(23, 59, 59, 999) : null;

    return this.solicitacoes.filter(solicitacao => {
      const dataSolicitacao = new Date(solicitacao.dataHora).getTime();

      // Check if dataInicio and dataFim are set, and filter accordingly
      if (inicio && fim) {
        return dataSolicitacao >= inicio && dataSolicitacao <= fim;
      } else if (inicio) {
        return dataSolicitacao >= inicio;
      } else if (fim) {
        return dataSolicitacao <= fim;
      } else {
        return true; // Include all if no dates are set
      }
    });
  }

  gerarRelatorioReceitas() {
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.getWidth();

    // Centralizing the title
    doc.text('Relatório de Receitas por Período', pageWidth / 2, 10, { align: 'center' });

    const solicitacoesFiltradas = this.filtrarSolicitacoesPorData();

    // Group solicitations by day
    const groupedByDate = solicitacoesFiltradas.reduce((acc, curr) => {
      if (curr.precoOrcado !== undefined) {
        const dateKey = new Date(curr.dataHora).toISOString().split('T')[0];
        if (!acc[dateKey]) acc[dateKey] = [];
        acc[dateKey].push(curr);
      }
      return acc;
    }, {} as { [key: string]: Solicitacao[] });

    const dataTable: any[] = [];
    let totalGeral = 0; // Total revenue across all periods

    for (const [date, solicitacoes] of Object.entries(groupedByDate)) {
      solicitacoes.forEach(solicitacao => {
        dataTable.push([
          date,
          solicitacao.nomeCliente ?? 'N/A',
          solicitacao.descricaoEquipamento,
          `R$ ${solicitacao.precoOrcado}`
        ]);
        totalGeral += solicitacao.precoOrcado ?? 0; // Accumulate the total revenue
      });
    }

    // Add the overall total row
    dataTable.push(['', '', 'Total Geral', `R$ ${totalGeral}`]);

    (doc as any).autoTable({
      head: [['Data', 'Cliente', 'Descrição', 'Valor']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-periodo.pdf');
  }

  gerarRelatorioReceitasPorCategoria() {
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.getWidth();

    // Centralizing the title
    doc.text('Relatório de Receitas por Categoria', pageWidth / 2, 10, { align: 'center' });

    // Include all solicitations with a valid `precoOrcado`
    const solicitacoesComReceita = this.solicitacoes.filter(solicitacao => solicitacao.precoOrcado !== undefined);

    // Group solicitations by category
    const groupedByCategory = solicitacoesComReceita.reduce((acc, curr) => {
      const categoria = curr.categoria ?? 'Sem Categoria'; // Ensure there's a category value
      if (!acc[categoria]) acc[categoria] = 0;
      acc[categoria] += curr.precoOrcado ?? 0;
      return acc;
    }, {} as { [key: string]: number });

    const dataTable: any[] = [];

    // Add rows for each category and its total
    for (const [categoria, total] of Object.entries(groupedByCategory)) {
      dataTable.push([categoria, `R$ ${total}`]);
    }

    (doc as any).autoTable({
      head: [['Categoria', 'Total']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-categoria.pdf');
  }
}
