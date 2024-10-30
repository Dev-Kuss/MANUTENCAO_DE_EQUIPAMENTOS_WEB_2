import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/login'; // URL do endpoint de login no back-end

  constructor(private http: HttpClient) {}

  // Método para fazer login e armazenar o token JWT
  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };

    return this.http.post<any>(this.apiUrl, body, { headers }).pipe(
      tap(response => {
        // Armazena o token JWT no Local Storage
        if (response.token) {
          localStorage.setItem('token', response.token);
        }
      })
    );
  }

  // Método para fazer logout e remover o token
  logout(): void {
    localStorage.removeItem('token'); // Remove o token do Local Storage
  }

  // Método para obter o token armazenado
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Método para verificar se o usuário está autenticado
  isAuthenticated(): boolean {
    const token = this.getToken();
    // Verifica se o token existe
    return !!token;
  }
}
