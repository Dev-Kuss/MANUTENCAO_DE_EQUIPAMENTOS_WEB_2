import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../models/categorias.model';
import { BaseModalComponent } from '../../components/base-modal/base-modal.component'; // Certifique-se de que o caminho está correto


@Component({
  selector: 'app-categorias-crud',
  templateUrl: './categorias-crud.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    FontAwesomeModule,
    BaseModalComponent
  ]
})
export class CategoriasCrudComponent implements OnInit {
  faEdit = faEdit;
  faTrash = faTrash;

  categorias: Categoria[] = [];
  categoriaSelecionada: Categoria | null = null;
  isCategoriaModalOpen = false;

  private categoriaService = inject(CategoriaService); // Injeção de dependência em Standalone

  ngOnInit(): void {
    this.loadCategorias();
  }

  // Carregar categorias do back-end
  loadCategorias() {
    this.categoriaService.getCategorias().subscribe(data => {
      this.categorias = data;
    });
  }

  // Abre o modal para adicionar ou editar
  abrirModalCategoria(categoria?: Categoria) {
    // Se uma categoria for passada, significa que estamos editando; caso contrário, estamos criando.
    if (categoria) {
      this.categoriaSelecionada = { ...categoria }; // Copia a categoria existente para edição
    } else {
      this.categoriaSelecionada = { id: 0, nome_categoria: '', ativo: true}; // Inicializa um objeto vazio para criação
    }
    this.isCategoriaModalOpen = true;
  }

  // Fecha o modal
  fecharModalCategoria() {
    this.isCategoriaModalOpen = false;
    this.categoriaSelecionada = null; // Reseta a categoria selecionada
  }

  // Salva ou edita uma categoria
  salvarCategoria() {
    if (this.categoriaSelecionada) {
      if (this.categoriaSelecionada.id) {
        // Atualiza a categoria existente
        this.categoriaService.updateCategoria(this.categoriaSelecionada.id, this.categoriaSelecionada)
          .subscribe(() => this.loadCategorias());
      } else {
        // Cria uma nova categoria
        this.categoriaService.createCategoria(this.categoriaSelecionada)
          .subscribe(() => this.loadCategorias());
      }
    }
    this.fecharModalCategoria(); // Fecha o modal após salvar
  }

  // Carrega a categoria para edição
  editarCategoria(categoria: Categoria) {
    this.abrirModalCategoria(categoria);
  }

  // Remove a categoria pelo id
  removerCategoria(categoria: Categoria) {
    const confirmacao = window.confirm(`Tem certeza de que deseja remover a categoria "${categoria.nome_categoria}"?`);

    if (confirmacao) {
      this.categoriaService.deleteCategoria(categoria.id)
        .subscribe(() => this.loadCategorias());
    }
  }
}
