import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { Funcionario } from '../../models/funcionario.model';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FuncionarioService } from '../../services/funcionario.service';

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
export class FuncionarioCrudComponent implements OnInit {
  faEdit = faEdit;
  faTrash = faTrash;
  isFuncionarioModalOpen = false;
  funcionarios: Funcionario[] = [];
  funcionarioSelecionado!: Funcionario;
  funcionarioLogado!: Funcionario;
  

  constructor(private funcionarioService: FuncionarioService) {}

  ngOnInit() {
    this.carregarFuncionarios();
  }

  carregarFuncionarios() {
    this.funcionarioService.getAllFuncionarios().subscribe(funcionarios => {
      this.funcionarios = funcionarios;
    });
  }

  abrirModalFuncionario() {
    this.funcionarioSelecionado = { id: '', nome: '', email: '', dataNascimento: '', senha: '' };
    this.isFuncionarioModalOpen = true;
  }
  

  fecharModalFuncionario() {
    this.isFuncionarioModalOpen = false;
  }

  salvarFuncionario() {
    if (this.funcionarioSelecionado) {
      if (this.funcionarioSelecionado.id) {
        this.funcionarioService
          .updateFuncionario(this.funcionarioSelecionado.id, this.funcionarioSelecionado)
          .subscribe(() => {
            this.carregarFuncionarios();
            this.fecharModalFuncionario();
          });
      } else {
        this.funcionarioService
          .createFuncionario(this.funcionarioSelecionado)
          .subscribe(() => {
            this.carregarFuncionarios();
            this.fecharModalFuncionario();
          });
      }
    }
  }
  ////

  editarFuncionario(funcionario: Funcionario) {
    this.funcionarioSelecionado = { ...funcionario };
    this.isFuncionarioModalOpen = true;
  }

  removerFuncionario(funcionario: Funcionario) {
    const confirmacao = window.confirm(`Tem certeza de que deseja remover o funcionário "${funcionario.nome}"?`);
    if (confirmacao) {
      this.funcionarioService.deleteFuncionario(funcionario.id.toString()).subscribe(() => {
        this.carregarFuncionarios();
      });
    }
  }
}
