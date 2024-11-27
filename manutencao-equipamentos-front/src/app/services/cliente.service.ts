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
  createCliente(data: ClienteRequestDTO): Observable<ClienteResponseDTO> {
    return this.http.post<ClienteResponseDTO>(`${this.apiUrl}/create`, data)
      .pipe(catchError(this.handleError));
  }

  // Lista todos os clientes
  getAllClientes(): Observable<ClienteResponseDTO[]> {
    return this.http.get<ClienteResponseDTO[]>(`${this.apiUrl}/read-all`)
      .pipe(catchError(this.handleError));
  }

  // Obtém um cliente por ID
  getClienteById(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.apiUrl}/read/${id}`)
      .pipe(catchError(this.handleError));
  }

  // Atualiza um cliente
  updateCliente(id: string, data: ClienteRequestDTO): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.apiUrl}/update/${id}`, data)
      .pipe(catchError(this.handleError));
  }

  // Exclui um cliente
  deleteCliente(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`)
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