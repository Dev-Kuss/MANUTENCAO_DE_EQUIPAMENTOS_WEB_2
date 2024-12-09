import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Solicitacao } from '../../models/solicitacao.model';
import { Orcamento } from "../../models/orcamento.model";
import { Funcionario } from '../../models/funcionario.model';

@Component({
  selector: 'app-visualizar-servico',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './visualizar-servico.component.html',
})

export class VisualizarServicoComponent {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() orcamento: Orcamento | null = null;
  @Input() funcionarioLogado!: Funcionario;

  resgatarServico(solicitacao: Solicitacao) {
    const previousEstado = solicitacao.estado;
    solicitacao.estado = 'APROVADA';
  
    if (!solicitacao.historicos) {
        solicitacao.historicos = [];
    }
  
    solicitacao.historicos.push({
        dataHora: new Date(),
        descricao: `Solicitação passou de ${previousEstado} para APROVADA.`,
        idFuncionario: this.funcionarioLogado.id,
        nomeFuncionario: "",
    });
  
    alert('Serviço resgatado com sucesso. A solicitação foi aprovada novamente.');
  }
}
