import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitacao } from '../models/solicitacao.model';


@Injectable({
  providedIn: 'root',
})
export class MostrarOrcamentoService {
  private apiUrl = `http://localhost:8080/solicitacao`;

  constructor(private http: HttpClient) {}

  aprovarSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    const updates = {
      estado: 'APROVADA'
    };

    return this.http.patch<Solicitacao>(
      `${this.apiUrl}/update/${solicitacao.idSolicitacao}`,
      updates,
      { headers }
    );
  }

  rejeitarSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    const updates = {
      estado: 'REJEITADA'
    };

    return this.http.patch<Solicitacao>(
      `${this.apiUrl}/update/${solicitacao.idSolicitacao}`,
      updates,
      { headers }
    );
  }
}
