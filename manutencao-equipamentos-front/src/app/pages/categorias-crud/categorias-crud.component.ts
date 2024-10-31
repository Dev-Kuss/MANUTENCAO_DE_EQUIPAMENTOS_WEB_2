import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { BaseModalComponent } from '../../components/base-modal/base-modal.component';
import { Categoria } from '../../models/categorias.model';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-categorias-crud',
  templateUrl: './categorias-crud.component.html',
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

export class CategoriasCrudComponent implements OnInit {
  faEdit = faEdit;
  faTrash = faTrash;
  
  categorias: Categoria[] = [
    { id: 1, nome: 'Notebook' },
    { id: 2, nome: 'Impressora' },
    { id: 3, nome: 'Desktop' },
    { id: 4, nome: 'Monitor' },
    { id: 5, nome: 'Smartphone' },
    { id: 6, nome: 'Tablet' },
    { id: 7, nome: 'Teclado' },
    { id: 8, nome: 'Mouse' },
    { id: 9, nome: 'Scanner' },
    { id: 10, nome: 'Roteador' }
  ];

  categoriaForm: FormGroup;
  categoriaSelecionada: Categoria | null = null;
  isCategoriaModalOpen = false;

  constructor(private fb: FormBuilder) {
    this.categoriaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  ngOnInit(): void {}

  abrirModalCategoria(categoria?: Categoria) {
    const nextId = this.categorias.length > 0 ? Math.max(...this.categorias.map(c => c.id)) + 1 : 1;
    this.categoriaSelecionada = categoria ? { ...categoria } : { id: nextId, nome: '' };
    
    // Set form control values based on the selected category
    this.categoriaForm.patchValue({ nome: this.categoriaSelecionada.nome });
    
    this.isCategoriaModalOpen = true;
  }

  fecharModalCategoria() {
    this.isCategoriaModalOpen = false;
    this.categoriaForm.reset(); // Reset the form
  }

  salvarCategoria() {
    if (this.categoriaForm.valid) {
      if (this.categoriaSelecionada) {
        const index = this.categorias.findIndex(cat => cat.id === this.categoriaSelecionada!.id);

        if (index !== -1) {
          // Update existing category
          this.categorias[index] = { id: this.categoriaSelecionada.id, nome: this.categoriaForm.value.nome };
        } else {
          // Add new category
          this.categorias.push({ id: this.categoriaSelecionada.id, nome: this.categoriaForm.value.nome });
        }
      }

      this.fecharModalCategoria();
    } else {
      console.log('Formulário inválido');
    }
  }

  editarCategoria(categoria: Categoria) {
    this.abrirModalCategoria(categoria);
  }

  removerCategoria(categoria: Categoria) {
    const confirmacao = window.confirm(`Tem certeza de que deseja remover a categoria "${categoria.nome}"?`);
    
    if (confirmacao) {
      this.categorias = this.categorias.filter(cat => cat.id !== categoria.id);
    }
  }
}
