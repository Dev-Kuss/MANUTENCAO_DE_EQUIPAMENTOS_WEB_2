import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
})

export class LoginComponent {
  loginForm: FormGroup;
  loginError = false; 

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')?.value;
      const senha = this.loginForm.get('senha')?.value;

      // Exemplo de validação estática (substituir por chamada à API ou serviço real)
      if (email === 'user@example.com' && senha === '1234') {
        // Redirecionar para a página do perfil do usuário com base no e-mail
        console.log('Login bem-sucedido!');
        this.loginError = false;
      } else {
        // Exibir mensagem de erro
        this.loginError = true;
      }
    }
  }
}