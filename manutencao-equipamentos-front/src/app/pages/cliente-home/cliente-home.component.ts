import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEye, faFileInvoiceDollar, faUndoAlt, faMoneyBillWave } from '@fortawesome/free-solid-svg-icons';

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { SolicitarManutencaoComponent } from "../../components/solicitar-manutencao/solicitar-manutencao.component";
import { MostrarOrcamentoComponent } from '../../components/mostrar-orcamento/mostrar-orcamento.component';
import { VisualizarServicoComponent } from '../../components/visualizar-servico/visualizar-servico.component';
import { PagarServicoComponent } from '../../components/pagar-servico/pagar-servico.component';

import { Solicitacao } from '../../models/solicitacao.model';
import { SolicitacaoService } from '../../services/solicitacao.service';

@Component({
  selector: 'app-cliente-home',
  templateUrl: './cliente-home.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    BaseModalComponent,
    SolicitarManutencaoComponent,
    MostrarOrcamentoComponent,
    VisualizarServicoComponent,
    PagarServicoComponent
  ]
})
export class ClienteHomeComponent implements OnInit {
  // Icons
  faEye = faEye;
  faFileInvoiceDollar = faFileInvoiceDollar;
  faUndoAlt = faUndoAlt;
  faMoneyBillWave = faMoneyBillWave;

  isManutencaoModalOpen = false;
  isOrcamentoModalOpen = false;
  isVisualizarModalOpen = false;
  isPagamentoModalOpen = false;

  solicitacaoSelecionada: Solicitacao | null = null;
  solicitacoes: Solicitacao[] = [];
  categorias = ['Notebook', 'Desktop', 'Impressora', 'Mouse', 'Teclado'];

  // Injeta o serviço de solicitação no componente
  constructor(private solicitacaoService: SolicitacaoService) {}

  // Carrega as solicitações ao inicializar o componente
  ngOnInit() {
    this.loadSolicitacoes();
  }

  // Método para carregar solicitações do backend
  loadSolicitacoes() {
    this.solicitacaoService.listarSolicitacoes().subscribe(
      (data) => {
        this.solicitacoes = data;
      },
      (error) => {
        console.error("Erro ao carregar solicitações:", error);
      }
    );
  }

  // Métodos de abertura e fechamento de modais
  abrirManutencaoModal() {
    this.isManutencaoModalOpen = true;
  }

  fecharManutencaoModal() {
    this.isManutencaoModalOpen = false;
  }

  abrirOrcamentoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isOrcamentoModalOpen = true;
  }

  fecharOrcamentoModal() {
    this.isOrcamentoModalOpen = false;
  }

  abrirVisualizarModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isVisualizarModalOpen  = true;
  }

  fecharVisualizarModal() {
    this.isVisualizarModalOpen  = false;
  }

  abrirPagamentoModal(solicitacao: Solicitacao) {
    this.solicitacaoSelecionada = solicitacao;
    this.isPagamentoModalOpen = true;
  }

  fecharPagamentoModal() {
    this.isPagamentoModalOpen = false;
  }

  // Método de visualização do serviço
  visualizarServico(solicitacao: Solicitacao) {
    console.log(`Visualizando solicitação: ${solicitacao.descricaoEquipamento}`);
    this.abrirVisualizarModal(solicitacao);
    // TODO: Navegar para a página de visualização da solicitação (RF008)
  }

  // Atualiza o estado de uma solicitação e salva a mudança no backend
  resgatarServico(solicitacao: Solicitacao) {
    const previousEstado = solicitacao.estado;
    solicitacao.estado = 'APROVADA';

    if (!solicitacao.historico) {
      solicitacao.historico = [];
    }

    solicitacao.historico.push({
      dataHora: new Date(),
      descricao: `Solicitação passou de ${previousEstado} para APROVADA.`,
      funcionario: 'Cliente',
    });

    this.solicitacaoService.updateSolicitacao(solicitacao.id!, solicitacao).subscribe(
      () => {
        alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
        this.loadSolicitacoes(); // Recarrega a lista de solicitações atualizada
      },
      (error) => {
        console.error('Erro ao atualizar a solicitação:', error);
      }
    );
  }

  // Método para pagamento do serviço
  pagarServico() {
    console.log('Pagar Serviço');
    // TODO: Implementar lógica para RF010
  }

  onPagamentoConfirmado() {
    this.fecharPagamentoModal();
    // Optional: Additional actions after payment confirmation
  }
}
