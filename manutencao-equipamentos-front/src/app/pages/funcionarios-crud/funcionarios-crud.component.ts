import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { Funcionario } from '../../models/funcionario.model';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-funcionario-crud',
  templateUrl: './funcionarios-crud.component.html',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    FontAwesomeModule,
    BaseModalComponent
  ]
})
export class FuncionarioCrudComponent {
  faEdit = faEdit;
  faTrash = faTrash;
  isFuncionarioModalOpen = false;

  funcionarios: Funcionario[] = [
    { id: 1, nome: 'João Silva', email: 'joao@empresa.com', dataNascimento: '1990-01-01', senha: 'senha123' },
    { id: 2, nome: 'Maria Silva', email: 'maria@empresa.com', dataNascimento: '1990-10-16', senha: 'senha123' },  
  ];

  funcionarioSelecionado: Funcionario | null = null
  funcionarioLogado = 'joao@empresa.com';

  abrirModalFuncionario() {
    // Cria um novo funcionário vazio com id incremental para novos
    const nextId = this.funcionarios.length > 0 ? Math.max(...this.funcionarios.map(f => f.id)) + 1 : 1;
    this.funcionarioSelecionado = { id: nextId, nome: '', email: '', dataNascimento: '', senha: '' };
    this.isFuncionarioModalOpen = true;
  }

  fecharModalFuncionario() {
    this.isFuncionarioModalOpen = false;
  }

  salvarFuncionario() {
    if (this.funcionarioSelecionado) {
      const index = this.funcionarios.findIndex(func => func.id === this.funcionarioSelecionado!.id);

      if (index !== -1) {
        this.funcionarios[index] = { ...this.funcionarioSelecionado };
      } else {
        this.funcionarios.push({ ...this.funcionarioSelecionado });
      }
    }

    // Fecha o modal após salvar
    this.fecharModalFuncionario();
  }

  editarFuncionario(funcionario: Funcionario) {
    this.funcionarioSelecionado = { ...funcionario };
    this.isFuncionarioModalOpen = true;
  }

  removerFuncionario(funcionario: Funcionario) {
    const confirmacao = window.confirm(`Tem certeza de que deseja remover o funcionário "${funcionario.nome}"?`);
    
    if(confirmacao) {
      this.funcionarios = this.funcionarios.filter(func => func.id !== funcionario.id);
    }
  }
}
