import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-autocadastro',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './autocadastro.component.html',
})

export class AutocadastroComponent {
  autocadastroForm: FormGroup;

  private apiUrl = 'http://localhost:8080/cliente';

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

  onSubmit() {
    if (this.autocadastroForm.valid) {
      const senhaAleatoria = Math.floor(1000 + Math.random() * 9000);
      const formData = {
        ...this.autocadastroForm.value,
        senha: senhaAleatoria
      };

      this.http.post(`${this.apiUrl}/create`, formData).subscribe({
        next: (response) => {
          console.log('Formulário enviado com sucesso:', response);
          alert('Auto cadastro realizado com sucesso! Sua senha foi enviada para o e-mail: ' + formData.email);
        },
        error: (error) => {
          if (error.status === 409) {
            console.error('Erro: Cliente já cadastrado.', error);
            alert("Já existe um Cliente cadastrado com o CPF:" + formData.cpf + " Por favor, verifique os dados e tente novamente.");
          } else {
            console.error('Erro ao enviar formulário:', error);
            alert('Ocorreu um erro ao realizar o autocadastro. Por favor, tente novamente.');
          }
        }
      });
    } else {
      alert('Por favor, preencha todos os campos corretamente.');
    }
  }

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
