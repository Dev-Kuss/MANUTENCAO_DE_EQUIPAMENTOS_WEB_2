import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BaseModalComponent } from '../base-modal/base-modal.component';
import { CategoriaService } from '../../services/categoria.service';
import { SolicitacaoService } from '../../services/solicitacao.service';
import { Solicitacao } from '../../models/solicitacao.model';
import { ClienteService } from '../../services/cliente.service';
import { Cliente } from '../../models/cliente.model';

@Component({
  selector: 'app-solicitar-manutencao',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,],
  templateUrl:  './solicitar-manutencao.component.html',
})

export class SolicitarManutencaoComponent implements OnInit {
  @Input() fecharModal!: () => void;
  @Output() solicitacaoCriada = new EventEmitter<void>();
  
  solicitacaoForm: FormGroup;
  categorias: { idCategoria: number; nome: string }[] = [];
  clienteAtual: Cliente | null = null;


  constructor(private fb: FormBuilder, private categoriaService: CategoriaService, private solicitacaoService: SolicitacaoService, private clienteService: ClienteService) {
    this.solicitacaoForm = this.fb.group({
      descricaoEquipamento: ['', [Validators.required]],
      categoriaEquipamento: ['', [Validators.required]],
      descricaoDefeito: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.categoriaService.getCategorias().subscribe(categorias => {
      this.categorias = categorias.map(c => ({
        idCategoria: c.id,
        nome: c.nome_categoria
      }));
    });
    this.clienteService.getClienteById(localStorage.getItem('id') ?? '').subscribe({
      next: (cliente) => {
          this.clienteAtual = cliente;
      },
      error: (err) => {
          console.error('Erro ao carregar cliente:', err);
      }
  });
  }
  
  registrarSolicitacao() {
    if (this.solicitacaoForm.valid) {
        const categoriaEquipamentoValue = this.solicitacaoForm.value.categoriaEquipamento;
        console.log('Valor de categoriaEquipamento:', categoriaEquipamentoValue);

        const categoriaId = Number(categoriaEquipamentoValue);
        const categoriaSelecionada = this.categorias.find(c => c.idCategoria === categoriaId);

        if (!categoriaSelecionada) {
            console.error('Categoria selecionada não encontrada');
            console.log('Categorias disponíveis:', this.categorias);
            return;
        }

        const novaSolicitacao: Solicitacao = {
            idSolicitacao: 1,
            dataHora: new Date(),
            descricaoEquipamento: this.solicitacaoForm.value.descricaoEquipamento,
            descricaoDefeito: this.solicitacaoForm.value.descricaoDefeito,
            estado: 'ABERTA',
            dataPagamento: undefined,
            dataHoraFinalizacao: undefined,
            idCliente: localStorage.getItem('id') ?? '',
            cliente: this.clienteAtual!,
            idCategoria: categoriaSelecionada.idCategoria,
            idResponsavel: '',
            categoria: { id: categoriaSelecionada.idCategoria, nome_categoria: categoriaSelecionada.nome, ativo: true }
        }; 

        console.log('Valor da categoria selecionada:', categoriaSelecionada);

        this.solicitacaoService.createSolicitacao(novaSolicitacao).subscribe(
          response => {
              console.log('Solicitação registrada com sucesso:', response);
              this.solicitacaoCriada.emit();
              if (this.fecharModal) {
                  this.fecharModal();
              }
          },
          error => {
              console.error('Erro ao registrar solicitação:', error);
              if (error.error) {
                  console.error('Detalhes do erro:', error.error);
              }
          }
      );
      
    } else {
        console.log('Formulário inválido');
    }
  }

}
