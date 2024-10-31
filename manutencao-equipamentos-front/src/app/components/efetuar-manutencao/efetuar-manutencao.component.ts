import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Solicitacao, Historico } from '../../models/solicitacao.model';

@Component({
  selector: 'app-efetuar-manutencao',
  templateUrl: './efetuar-manutencao.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EfetuarManutencaoComponent implements OnInit {
  @Input() solicitacao: Solicitacao | null = null;
  @Input() funcionarios: string[] = []; 
  @Input() funcionarioLogado: string = '';

  manutencaoForm: FormGroup;
  redirecionamentoForm: FormGroup;
  
  abaSelecionada: string = 'manutencao'; 

  constructor(private fb: FormBuilder) {
    this.manutencaoForm = this.fb.group({
      descricaoManutencao: ['', [Validators.required, Validators.minLength(10)]],
      orientacoesCliente: ['']
    });

    this.redirecionamentoForm = this.fb.group({
      funcionarioDestino: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {}

  confirmarManutencao() {
    if (this.solicitacao && this.manutencaoForm.valid) {
      this.solicitacao.estado = 'AGUARDANDO PAGAMENTO';

      const historico: Historico = {
        dataHora: new Date(),
        descricao: `Manutenção realizada: ${this.manutencaoForm.value.descricaoManutencao}. Orientações: ${this.manutencaoForm.value.orientacoesCliente}`,
        funcionario: this.funcionarioLogado
      };
      this.solicitacao.historico?.push(historico);

      console.log('Manutenção registrada com sucesso:', this.solicitacao);
    }
  }

  confirmarRedirecionamento() {
    if (this.solicitacao && this.redirecionamentoForm.valid && this.redirecionamentoForm.value.funcionarioDestino !== this.funcionarioLogado) {
      this.solicitacao.estado = 'REDIRECIONADA';

      const historico: Historico = {
        dataHora: new Date(),
        descricao: `Solicitação redirecionada de ${this.funcionarioLogado} para ${this.redirecionamentoForm.value.funcionarioDestino}`,
        funcionario: this.funcionarioLogado
      };
      this.solicitacao.historico?.push(historico);

      console.log('Solicitação redirecionada com sucesso:', this.solicitacao);
    }
  }
}
