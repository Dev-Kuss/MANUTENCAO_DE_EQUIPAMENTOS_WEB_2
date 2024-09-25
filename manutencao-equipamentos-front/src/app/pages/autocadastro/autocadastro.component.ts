import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-autocadastro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './autocadastro.component.html',
  styleUrl: './autocadastro.component.css'
})

export class AutocadastroComponent {
  autocadastroForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
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
      this.http.get(`https://viacep.com.br/ws/${cep}/json/`).subscribe(
        (dados: any) => {
          if (!dados.erro) {
            this.autocadastroForm.patchValue({
              logradouro: dados.logradouro,
              bairro: dados.bairro,
              cidade: dados.localidade,
              estado: dados.uf
            });
          } else {
            this.limparEndereco();
            alert('CEP não encontrado.')
          }
        },
        (error) => {
          console.error('Erro ao buscar o CEP', error);
          this.limparEndereco();
        }
      );
    }
  }

  limparEndereco() {
    this.autocadastroForm.patchValue({
      logradouro: '',
      bairro: '',
      cidade: '',
      estado: ''
    });
  }
}
