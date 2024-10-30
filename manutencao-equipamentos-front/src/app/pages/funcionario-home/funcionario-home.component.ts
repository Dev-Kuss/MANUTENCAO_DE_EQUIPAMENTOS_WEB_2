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

    // Centralizando o título
    doc.text('Relatório de Receitas por Período', pageWidth / 2, 10, { align: 'center' });

    const solicitacoesFiltradas = this.filtrarSolicitacoesPorData();

    const groupedByDate = solicitacoesFiltradas.reduce((acc, curr) => {
      // Apenas incluir se precoOrcado existir
      if (curr.precoOrcado !== undefined) {
        const dateKey = curr.dataHora.toISOString().split('T')[0];
        if (!acc[dateKey]) acc[dateKey] = [];
        acc[dateKey].push(curr);
      }
      return acc;
    }, {} as { [key: string]: Solicitacao[] });

    const dataTable: any[] = [];

    for (const [date, solicitacoes] of Object.entries(groupedByDate)) {
      const totalDia = solicitacoes.reduce((sum, s) => sum + (s.precoOrcado ?? 0), 0); // Usando precoOrcado
      solicitacoes.forEach(solicitacao => {
        dataTable.push([date, solicitacao.nomeCliente, solicitacao.descricaoEquipamento, `R$ ${solicitacao.precoOrcado}`]);
      });
      dataTable.push([`${date} - Total`, '', '', `R$ ${totalDia}`]); // Adicionando R$
    }

    (doc as any).autoTable({
      head: [['Data', 'Cliente', 'Descrição', 'Valor']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-periodo.pdf');
  }

  // RF020 - Geração de Relatório de Receitas por Categoria
  gerarRelatorioReceitasPorCategoria() {
    const doc = new jsPDF();
    const pageWidth = doc.internal.pageSize.getWidth();

    // Centralizando o título
    doc.text('Relatório de Receitas por Categoria', pageWidth / 2, 10, { align: 'center' });

    const groupedByCategory = this.solicitacoes.reduce((acc, curr) => {
      // Apenas incluir se precoOrcado existir
      if (curr.precoOrcado !== undefined) {
        const categoria = curr.categoria ?? 'Sem Categoria'; // Garantindo que haja um valor para categoria
        if (!acc[categoria]) acc[categoria] = [];
        acc[categoria].push(curr);
      }
      return acc;
    }, {} as { [key: string]: Solicitacao[] });

    const dataTable: any[] = [];

    for (const [categoria, solicitacoes] of Object.entries(groupedByCategory)) {
      const totalCategoria = solicitacoes.reduce((sum, s) => sum + (s.precoOrcado ?? 0), 0); // Usando precoOrcado
      solicitacoes.forEach(solicitacao => {
        dataTable.push([categoria, solicitacao.nomeCliente, solicitacao.descricaoEquipamento, `R$ ${solicitacao.precoOrcado}`]);
      });
      dataTable.push([`${categoria} - Total`, '', '', `R$ ${totalCategoria}`]); // Adicionando R$
    }

    (doc as any).autoTable({
      head: [['Categoria', 'Cliente', 'Descrição', 'Valor']],
      body: dataTable,
    });

    doc.save('relatorio-receitas-categoria.pdf');
  }
}
