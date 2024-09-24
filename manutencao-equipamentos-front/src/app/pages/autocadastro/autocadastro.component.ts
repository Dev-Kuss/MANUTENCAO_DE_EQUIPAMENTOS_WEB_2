import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-autocadastro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './autocadastro.component.html',
  styleUrl: './autocadastro.component.css'
})

export class AutocadastroComponent {
  autocadastroForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.autocadastroForm = this.fb.group({
      cpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefone: ['', Validators.required],
      cep: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
      logradouro: ['', Validators.required],
      bairro: ['', Validators.required],
      cidade: ['', Validators.required],
      estado: ['', Validators.required]
    });
  }

  // TODO: substituir pelo cadastro integrado ao backend/bd
  onSubmit() {
    if (this.autocadastroForm.valid) {
      const senhaAleatoria = Math.floor(1000 + Math.random() * 9000);
      console.log('Formulário enviado:', this.autocadastroForm.value);
      console.log('Senha aleatória gerada:', senhaAleatoria);
      alert('Autocadastro realizado com sucesso! Sua senha é: ' + senhaAleatoria);
    } else {
      alert('Por favor, preencha todos os campos corretamente.');
    }
  }

  // TODO: substituir pela API ViaCEP
  buscarCEP() {
    const cep = this.autocadastroForm.get('cep')?.value;
    if (cep && cep.length === 8) {
      // Simular preenchimento dos campos de endereço
      this.autocadastroForm.patchValue({
        logradouro: 'Rua Exemplo',
        bairro: 'Bairro Exemplo',
        cidade: 'Cidade Exemplo',
        estado: 'Estado Exemplo'
      });
      console.log('Dados do endereço preenchidos para o CEP:', cep);
    } else {
      alert('Por favor, insira um CEP válido.');
    }
  }
}
