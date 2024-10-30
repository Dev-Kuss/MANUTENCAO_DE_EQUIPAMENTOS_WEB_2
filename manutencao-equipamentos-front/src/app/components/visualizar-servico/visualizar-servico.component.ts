import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Solicitacao } from '../../models/solicitacao.model';

@Component({
  selector: 'app-visualizar-servico',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './visualizar-servico.component.html',
})

export class VisualizarServicoComponent {
  @Input() solicitacao: Solicitacao | null = null;

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
  
    alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
  }
}
