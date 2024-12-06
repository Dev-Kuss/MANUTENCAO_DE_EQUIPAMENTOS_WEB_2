import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Cliente } from '../models/cliente.model'; 

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private apiUrl = 'http://localhost:8080/cliente'; // URL do backend

  constructor(private http: HttpClient) {}

  // Cria um novo cliente
  createCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.apiUrl}/create`, cliente)
      .pipe(catchError(this.handleError));
  }

  // Lista todos os clientes
  getAllClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.apiUrl}/read-all`)
      .pipe(catchError(this.handleError));
  }

  // Obtém um cliente por ID
  getClienteById(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.apiUrl}/read/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Atualiza um cliente
  updateCliente(id: string, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.apiUrl}/update/${id}`, cliente)
      .pipe(catchError(this.handleError));
  }

  // Tratamento de erros
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Erro desconhecido!';
    if (error.error instanceof ErrorEvent) {
      // Erro do lado do cliente
      errorMessage = `Erro: ${error.error.message}`;
    } else {
      // Erro do lado do servidor
      errorMessage = `Código de erro: ${error.status}, Mensagem: ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}