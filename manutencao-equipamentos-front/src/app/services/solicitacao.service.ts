import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitacao } from '../models/solicitacao.model';

@Injectable({
  providedIn: 'root'
})
export class SolicitacaoService {
  private apiUrl = 'http://localhost:8080/solicitacao'; // URL base para Solicitação

  constructor(private http: HttpClient) {}

  // Criar uma nova solicitação
  createSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.post<Solicitacao>(`${this.apiUrl}/create`, solicitacao);
  }

  // Listar todas as solicitações
  listarSolicitacoes(): Observable<Solicitacao[]> {
    return this.http.get<Solicitacao[]>(`${this.apiUrl}/read-all`);
  }

  // Obter uma solicitação por ID
  getSolicitacaoById(id: number): Observable<Solicitacao> {
    return this.http.get<Solicitacao>(`${this.apiUrl}/read/${id}`);
  }

  // Atualizar uma solicitação
  updateSolicitacao(id: number, solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.put<Solicitacao>(`${this.apiUrl}/update/${id}`, solicitacao);
  }

  // Excluir uma solicitação
  deleteSolicitacao(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
