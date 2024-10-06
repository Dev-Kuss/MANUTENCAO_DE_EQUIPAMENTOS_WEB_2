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
}
