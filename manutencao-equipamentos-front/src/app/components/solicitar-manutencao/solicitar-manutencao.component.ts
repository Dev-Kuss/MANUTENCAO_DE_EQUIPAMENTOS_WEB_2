import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BaseModalComponent } from '../base-modal/base-modal.component';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-solicitar-manutencao',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, BaseModalComponent],
  templateUrl:  './solicitar-manutencao.component.html',
})

export class SolicitarManutencaoComponent implements OnInit {
  @Input() fecharModal!: () => void;
  
  solicitacaoForm: FormGroup;
  categorias: string[] = [];

  constructor(private fb: FormBuilder, private categoriaService: CategoriaService) {
    this.solicitacaoForm = this.fb.group({
      descricaoEquipamento: ['', [Validators.required]],
      categoriaEquipamento: ['', [Validators.required]],
      descricaoDefeito: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.categoriaService.getCategorias().subscribe(categorias => {
      this.categorias = categorias.map(c => c.nome_categoria);
    });
  }
  
  registrarSolicitacao() {
    if (this.solicitacaoForm.valid) {
      const novaSolicitacao = {
        dataHora: new Date(),
        descricaoEquipamento: this.solicitacaoForm.value.descricaoEquipamento,
        descricaoDefeito: this.solicitacaoForm.value.descricaoDefeito,
        estado: 'ABERTA',
      };
      // TODO: Chamada ao back-end
      console.log('Nova solicitação registrada:', novaSolicitacao);
      this.fecharModal()
    } else {
      console.log('Formulário inválido');
    }
  }

}
