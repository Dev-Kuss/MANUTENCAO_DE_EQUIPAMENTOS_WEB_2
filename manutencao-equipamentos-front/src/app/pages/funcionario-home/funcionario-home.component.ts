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
  faCheckCircle,
  faSignOutAlt
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
export class FuncionarioHomeComponent implements OnInit {
  funcionarios: Funcionario[] = [];
  paginaAtual: number = 1;
  itensPorPagina: number = 10;

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
  faSignOutAlt = faSignOutAlt;

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

  solicitacoesFiltradas: Solicitacao[] = [...this.solicitacoes];

  constructor(
    private authService: AuthService,
    private funcionarioService: FuncionarioService,
    private solicitacaoService: SolicitacaoService,
  ) { }

  ngOnInit(): void {
    this.loadListaFuncionarios();
    this.loadSolicitacoes();
    this.nomeUsuario = this.authService.getNomeUsuario(); // Obtém o nome do usuário do AuthService
  }

  loadListaFuncionarios(): void {
    this.funcionarioService.getAllFuncionarios().subscribe((funcionarios: Funcionario[]) => {
      this.listaFuncionarios = funcionarios;
    });
  }

  loadSolicitacoes(): void {
    this.solicitacaoService.getSolicitacoes().subscribe({
      next: (solicitacoes: Solicitacao[]) => {
        // Aqui filtramos apenas as solicitações "ABERTA"
        this.solicitacoes = solicitacoes.filter(s => s.estado === 'ABERTA');
        this.solicitacoesFiltradas = [...this.solicitacoes];
      },
      error: (error: any) => {
        console.error('Erro ao carregar solicitações:', error);
      },
    });
  }

  logout() {
    window.location.href = '/login';
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
}
