import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Solicitacao, Orcamento } from '../../models/solicitacao.model';

@Component({
  selector: 'app-visualizar-servico',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './visualizar-servico.component.html',
})

export class VisualizarServicoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() orcamento: Orcamento | null = null;

  resgatarServico(solicitacao: Solicitacao) {
    const previousEstado = solicitacao.estado;
    solicitacao.estado = 'APROVADA';
  
    if (!solicitacao.historicos) {
        solicitacao.historicos = [];
    }
  
    solicitacao.historicos.push({
        dataHora: new Date(),
        descricao: `Solicitação passou de ${previousEstado} para APROVADA.`,
        funcionario: {
            idFuncionario: 1, // ajuste conforme necessário
            nome: 'Cliente'
        }
    });
  
    alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
  }
}
