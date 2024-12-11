import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError = false;
  showSuccessAnimation = false; 

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')?.value;
      const senha = this.loginForm.get('senha')?.value;

      this.authService.login(email, senha).subscribe(
        () => {
          console.log('Login bem-sucedido!');
          this.loginError = false;

          this.showSuccessAnimation = true;

          setTimeout(() => {
            const roles = JSON.parse(<string>localStorage.getItem('roles'));
            if (roles.includes('ADMIN') || roles.includes('EMPLOYEE')) {
              this.router.navigate(['/funcionario-home']);
            } else if (roles.includes('CLIENT')) {
              this.router.navigate(['/cliente-home']);
            }

            this.showSuccessAnimation = false;
          }, 2000);
        },
        (error: any) => {
          console.log('Erro de autenticação:', error);
          this.loginError = true;
        }
      );
    }
  }

  navigateToSignup() {
    this.router.navigate(['/autocadastro']);
  }
}
