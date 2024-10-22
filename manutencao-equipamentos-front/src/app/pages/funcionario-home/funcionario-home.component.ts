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

  solicitacoes: Solicitacao[] = [
    {
      dataHora: new Date('2024-10-22T10:30:00'),
      nomeCliente: 'Lucas Oliveira',
      descricaoEquipamento: 'Monitor Samsung 24"',
      estado: 'ABERTA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      categoria: 'Monitor'
    },
    {
      dataHora: new Date('2024-10-22T08:15:00'),
      nomeCliente: 'Ana Lima',
      descricaoEquipamento: 'MacBook Pro 2021',
      estado: 'ABERTA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      categoria: 'Notebook'
    },
    {
      dataHora: new Date('2024-10-21T13:45:00'),
      nomeCliente: 'Pedro Costa',
      descricaoEquipamento: 'Impressora HP LaserJet P1005',
      estado: 'ORÇADA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 200,
      categoria: 'Impressora'
    },
    {
      dataHora: new Date('2024-10-20T11:00:00'),
      nomeCliente: 'Camila Dias',
      descricaoEquipamento: 'Desktop Dell OptiPlex 3070',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 850,
      categoria: 'Desktop'
    },
    {
      dataHora: new Date('2024-10-19T15:30:00'),
      nomeCliente: 'Renata Souza',
      descricaoEquipamento: 'Notebook Lenovo Ideapad 3',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 900,
      categoria: 'Notebook'
    },
    {
      dataHora: new Date('2024-09-15T09:15:00'),
      nomeCliente: 'Claudio Martins',
      descricaoEquipamento: 'Impressora Epson EcoTank L3150',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 450,
      categoria: 'Impressora'
    },
    {
      dataHora: new Date('2024-09-10T17:45:00'),
      nomeCliente: 'Bruna Rocha',
      descricaoEquipamento: 'Monitor LG UltraWide 34"',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 500,
      categoria: 'Monitor'
    },
    {
      dataHora: new Date('2024-09-05T14:30:00'),
      nomeCliente: 'Felipe Almeida',
      descricaoEquipamento: 'Desktop HP ProDesk 400 G5',
      estado: 'REJEITADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 800,
      categoria: 'Desktop'
    },
    {
      dataHora: new Date('2024-08-25T12:00:00'),
      nomeCliente: 'Fernanda Lima',
      descricaoEquipamento: 'Notebook Dell Inspiron 14',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 950,
      categoria: 'Notebook'
    },
    {
      dataHora: new Date('2024-08-10T16:15:00'),
      nomeCliente: 'Gabriel Sousa',
      descricaoEquipamento: 'Impressora Brother DCP-L2540DW',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 400,
      categoria: 'Impressora'
    },
    {
      dataHora: new Date('2024-07-30T10:00:00'),
      nomeCliente: 'Rafael Figueiredo',
      descricaoEquipamento: 'Monitor Philips 27"',
      estado: 'AGUARDANDO PAGAMENTO',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 350,
      categoria: 'Monitor'
    },
    {
      dataHora: new Date('2024-07-15T09:30:00'),
      nomeCliente: 'Isabela Gomes',
      descricaoEquipamento: 'Desktop Lenovo ThinkCentre M720',
      estado: 'REDIRECIONADA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 750,
      categoria: 'Desktop'
    }
  ];

  // Colors mapping
  estadoCores: any = {
    'ABERTA': 'gray',
    'ORÇADA': 'brown',
    'REJEITADA': 'red',
    'APROVADA': 'yellow',
    'REDIRECIONADA': 'purple',
    'AGUARDANDO PAGAMENTO': 'blue',
    'PAGA': 'orange',
    'FINALIZADA': 'green'
  };

  filtroSelecionado: string = 'TODAS';
  dataInicio: Date | null = null;
  dataFim: Date | null = null;

  solicitacoesFiltradas: Solicitacao[] = [...this.solicitacoes];

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
