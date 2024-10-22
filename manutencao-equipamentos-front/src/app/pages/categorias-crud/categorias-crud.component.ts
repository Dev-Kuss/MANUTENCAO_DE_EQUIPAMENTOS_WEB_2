import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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
    FontAwesomeModule,
    BaseModalComponent
  ]
})

export class CategoriasCrudComponent {
  faEdit = faEdit;
  faTrash = faTrash;

  categorias: Categoria[] = [
    { id: 1, nome: 'Notebook' },
    { id: 2, nome: 'Impressora' },
    { id: 3, nome: 'Desktop' },
  ];

  // Categoria selecionada para edição/adicionar
  categoriaSelecionada: Categoria | null = null;
  isCategoriaModalOpen = false;

  // Abre o modal para adicionar ou editar
  abrirModalCategoria(categoria?: Categoria) {
    const nextId = this.categorias.length > 0 ? Math.max(...this.categorias.map(c => c.id)) + 1 : 1;
    this.categoriaSelecionada = categoria ? { ...categoria } : { id: nextId, nome: '' };
    this.isCategoriaModalOpen = true;
  }

  // Fecha o modal
  fecharModalCategoria() {
    this.isCategoriaModalOpen = false;
  }

  // Salva ou edita uma categoria
  salvarCategoria() {
    if (this.categoriaSelecionada) {
      const index = this.categorias.findIndex(cat => cat.id === this.categoriaSelecionada!.id);

      if (index !== -1) {
        // Atualiza categoria existente
        this.categorias[index] = { ...this.categoriaSelecionada };
      } else {
        // Adiciona nova categoria
        this.categorias.push({ ...this.categoriaSelecionada });
      }
    }

    // Fecha o modal após salvar
    this.fecharModalCategoria();
  }

  // Carrega a categoria para edição
  editarCategoria(categoria: Categoria) {
    this.abrirModalCategoria(categoria);
  }

  // Remove a categoria pelo id
  removerCategoria(categoria: Categoria) {
    this.categorias = this.categorias.filter(cat => cat.id !== categoria.id);
  }
}
