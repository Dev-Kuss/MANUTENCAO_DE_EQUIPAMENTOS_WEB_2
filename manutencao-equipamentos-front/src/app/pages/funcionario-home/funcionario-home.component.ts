import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
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
  faFilePdf
} from '@fortawesome/free-solid-svg-icons';

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
      dataHora: new Date('2024-10-01T09:00:00'),
      nomeCliente: 'João Silva',
      descricaoEquipamento: 'Notebook Dell Inspiron 15 3000',
      estado: 'ABERTA',
      historico: [],
      destinoRedirecionamento: 'João Silva'
    },
    {
      dataHora: new Date('2024-09-30T14:30:00'),
      nomeCliente: 'Maria Santos',
      descricaoEquipamento: 'Impressora HP LaserJet Pro M15w',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario'
    },
    {
      dataHora: new Date('2024-07-23T14:30:00'),
      nomeCliente: 'Maria Santos',
      descricaoEquipamento: 'Impressora HP LaserJet Pro M15w',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario'
    },
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

  constructor() { }

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

  // Filtering methods (RF013)
  filtroSelecionado: string = 'TODAS';
  dataInicio: Date | null = null;
  dataFim: Date | null = null;

  filtrarSolicitacoes() {
    // Implementar lógica de filtragem com base em HOJE, PERÍODO ou TODAS
  }

  // RF019 & RF020 - Gerar Relatórios
  gerarRelatorioReceitas() {
    // Lógica para gerar relatório de receitas em PDF
  }

  gerarRelatorioReceitasPorCategoria() {
    // Lógica para gerar relatório de receitas por categoria em PDF
  }

  // RF017 - Manter Categorias de Equipamento
  manterCategoriasEquipamento() {
    // Navegar para a página de manutenção de categorias
  }

  // RF018 - Manter Funcionários
  manterFuncionarios() {
    // Navegar para a página de manutenção de funcionários
  }
}
