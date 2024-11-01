import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitacao } from '../models/solicitacao.model';

@Injectable({
  providedIn: 'root'
})
export class SolicitacaoService {
  private apiUrl = 'http://localhost:8080/solicitacao'; // URL base do endpoint de solicitações

  constructor(private http: HttpClient) {}

  getSolicitacoes(usuarioId: string | null): Observable<Solicitacao[]> {
    return this.http.get<Solicitacao[]>(`${this.apiUrl}/read-all?usuarioId=${usuarioId}`);
  }

  getSolicitacaoById(id: string): Observable<Solicitacao> {
    return this.http.get<Solicitacao>(`${this.apiUrl}/read/${id}`);
  }

  createSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.post<Solicitacao>(`${this.apiUrl}/create`, solicitacao);
  }

  updateSolicitacao(id: number, solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.put<Solicitacao>(`${this.apiUrl}/update/${id}`, solicitacao);
  }

  deleteSolicitacao(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
