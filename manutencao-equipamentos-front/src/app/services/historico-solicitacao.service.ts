import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistoricoSolicitacao} from '../models/historico-solicitacao.model';


@Injectable({
  providedIn: 'root'
})
export class HistoricoSolicitacaoService {
  private apiUrl = 'http://localhost:8080/categoria-equipamento'; // Defina o URL do back-end

  constructor(private http: HttpClient) { }

  createHistorico(historico: HistoricoSolicitacao): Observable<HistoricoSolicitacao> {
    return this.http.post<HistoricoSolicitacao>(`${this.apiUrl}/create`, historico);
  }

  getAllHistoricos(): Observable<HistoricoSolicitacao[]> {
    return this.http.get<HistoricoSolicitacao[]>(`${this.apiUrl}/read-all`);
  }

  getHistoricoById(id: number): Observable<HistoricoSolicitacao> {
    return this.http.get<HistoricoSolicitacao>(`${this.apiUrl}/read/${id}`);
  }

  updateHistorico(id: number, historico: HistoricoSolicitacao): Observable<HistoricoSolicitacao> {
    return this.http.put<HistoricoSolicitacao>(`${this.apiUrl}/update/${id}`, historico);
  }

  deleteHistorico(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}