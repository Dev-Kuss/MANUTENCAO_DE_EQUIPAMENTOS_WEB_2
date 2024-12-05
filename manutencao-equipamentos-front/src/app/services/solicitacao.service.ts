import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Solicitacao } from '../models/solicitacao.model';
import { Orcamento } from '../models/orcamento.model';

@Injectable({
  providedIn: 'root'
})
export class SolicitacaoService {
  private apiUrl = 'http://localhost:8080/solicitacao'; // URL base do endpoint de solicitações
  private orcamentoUrl = 'http://localhost:8080/orcamento'; // Nova URL

  constructor(private http: HttpClient) {}

  getSolicitacoes(usuarioId?: string | null): Observable<Solicitacao[]> {
    const url = usuarioId 
      ? `${this.apiUrl}/read-all?usuarioId=${usuarioId}`
      : `${this.apiUrl}/read-all`;
    return this.http.get<Solicitacao[]>(url);
  }

  createSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.post<Solicitacao>(`${this.apiUrl}/create`, solicitacao);
  }

  updateSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    return this.http.put<Solicitacao>(`${this.apiUrl}/update/${solicitacao.idSolicitacao}`, solicitacao);
  }

  patchSolicitacao(id: number, updates: Partial<Solicitacao>): Observable<Solicitacao | any> {
    return this.http.patch<Solicitacao | any>(`${this.apiUrl}/update/${id}`, updates).pipe(
      catchError(this.handleError)
    );
  }

  deleteSolicitacao(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  createOrcamento(orcamento: Orcamento): Observable<Orcamento> {
    return this.http.post<Orcamento>(`${this.orcamentoUrl}/create`, orcamento);
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    if (error.error instanceof ErrorEvent) {
      console.error('Um erro ocorreu:', error.error.message);
    } else {
      console.error(
        `O servidor retornou um código ${error.status}, ` +
        `corpo do erro: ${JSON.stringify(error.error)}`
      );
    }
    return throwError(() => new Error('Ocorreu um erro; por favor, tente novamente mais tarde.'));
  }
}
