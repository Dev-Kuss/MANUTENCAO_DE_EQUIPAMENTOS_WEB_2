import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitacao } from '../models/solicitacao.model';


@Injectable({
  providedIn: 'root',
})
export class MostrarOrcamentoService {
  private apiUrl = `http://localhost:8080/solicitacao`;

  constructor(private http: HttpClient) {}

  aprovarSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    solicitacao.estado = 'ARRUMADA';
    return this.http.put<Solicitacao>(
      `${this.apiUrl}/update/${solicitacao.idSolicitacao}`,
      solicitacao
    );
  }

  rejeitarSolicitacao(solicitacao: Solicitacao): Observable<Solicitacao> {
    solicitacao.estado = 'REJEITADA';
    return this.http.put<Solicitacao>(
      `${this.apiUrl}/update/${solicitacao.idSolicitacao}`,
      solicitacao
    );
  }
}
