import { Component, OnInit } from '@angular/core';
import { DrawerService } from '../../services/modal.service';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule  } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  descricaoDefeito: string
  estado: string;
}

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  styleUrls: ['./modal.component.css'],
})

export class ModalComponent implements OnInit {
  solicitacaoForm: FormGroup;
  isDrawerOpen = false;
  categorias = ['Eletrônico', 'Eletrodoméstico', 'Mecânico', 'Outro']; 

  constructor(private fb: FormBuilder, private drawerService: DrawerService) {
    this.solicitacaoForm = this.fb.group({
      descricaoEquipamento: ['', [Validators.required]],
      categoriaEquipamento: ['', [Validators.required]],
      descricaoDefeito: ['', [Validators.required]],
    });
  }

  ngOnInit() {
    this.drawerService.drawerState$.subscribe((isOpen) => {
      this.isDrawerOpen = isOpen;
    });
  }

  registrarSolicitacao() {
    if (this.solicitacaoForm.valid) {
          const novaSolicitacao: Solicitacao = {
            dataHora: new Date(),
            descricaoEquipamento: this.solicitacaoForm.value.descricaoEquipamento,
            descricaoDefeito: this.solicitacaoForm.value.descricaoDefeito,
            estado: 'ABERTA'
          };
          // this.solicitacoes.push(novaSolicitacao);
          console.log('Nova solicitação registrada:', novaSolicitacao);
          this.fecharDrawer(); // Fecha o drawer após registrar
        } else {
          console.log('Formulário inválido');
        }
  }

  fecharDrawer() {
    this.drawerService.closeDrawer();
  }
}
