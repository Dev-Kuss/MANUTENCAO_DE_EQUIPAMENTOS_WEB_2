import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
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
    ReactiveFormsModule,
    FontAwesomeModule,
    BaseModalComponent
  ]
})
export class FuncionarioCrudComponent implements OnInit {
  faEdit = faEdit;
  faTrash = faTrash;
  isFuncionarioModalOpen = false;

  funcionarios: Funcionario[] = [
    { id: 1, nome: 'João Silva', email: 'joao@empresa.com', dataNascimento: '1990-01-01', senha: 'senha123' },
    { id: 2, nome: 'Maria Silva', email: 'maria@empresa.com', dataNascimento: '1990-10-16', senha: 'senha123' },  
  ];

  funcionarioForm: FormGroup;
  funcionarioSelecionado: Funcionario | null = null;
  funcionarioLogado = 'joao@empresa.com';

  constructor(private fb: FormBuilder) {
    this.funcionarioForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      dataNascimento: ['', [Validators.required]],
      senha: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {}

  abrirModalFuncionario() {
    const nextId = this.funcionarios.length > 0 ? Math.max(...this.funcionarios.map(f => f.id)) + 1 : 1;
    this.funcionarioSelecionado = { id: nextId, nome: '', email: '', dataNascimento: '', senha: '' };
    this.funcionarioForm.reset(); // Limpa o formulário para novos funcionários
    this.isFuncionarioModalOpen = true;
  }

  fecharModalFuncionario() {
    this.isFuncionarioModalOpen = false;
  }

  salvarFuncionario() {
    if (this.funcionarioForm.valid) {
      const { nome, email, dataNascimento, senha } = this.funcionarioForm.value;
      const novoFuncionario = {
        id: this.funcionarioSelecionado?.id || 0,
        nome,
        email,
        dataNascimento,
        senha
      };

      const index = this.funcionarios.findIndex(func => func.id === novoFuncionario.id);
      if (index !== -1) {
        this.funcionarios[index] = novoFuncionario;
      } else {
        this.funcionarios.push(novoFuncionario);
      }
      this.fecharModalFuncionario();
    } else {
      console.log('Formulário inválido');
    }
  }

  editarFuncionario(funcionario: Funcionario) {
    this.funcionarioSelecionado = { ...funcionario };
    this.funcionarioForm.patchValue(funcionario); // Carrega os dados para edição
    this.isFuncionarioModalOpen = true;
  }

  removerFuncionario(funcionario: Funcionario) {
    const confirmacao = window.confirm(`Tem certeza de que deseja remover o funcionário "${funcionario.nome}"?`);
    
    if (confirmacao) {
      this.funcionarios = this.funcionarios.filter(func => func.id !== funcionario.id);
    }
  }
}
