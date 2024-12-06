import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orcamento } from '../models/orcamento.model';

@Injectable({
  providedIn: 'root'
})
export class OrcamentoService {

  private apiUrl = 'http://localhost:8080/orcamento';
  
  constructor(private http: HttpClient) {}

  updateOrcamento(idOrcamento: string, orcamentoRequest: any): Observable<Orcamento> {
    const url = `${this.apiUrl}/update/${idOrcamento}`; 
    return this.http.put<Orcamento>(url, orcamentoRequest);
  }

  createOrcamento(orcamento: any): Observable<Orcamento> {
    return this.http.post<Orcamento>(`${this.apiUrl}/create`, orcamento);
  }

  listarOrcamentos(): Observable<any[]> {
    return this.http.get<Orcamento[]>(`${this.apiUrl}/read-all`);
  }

  getOrcamentoById(id: string): Observable<Orcamento> {
    return this.http.get<Orcamento>(`${this.apiUrl}/read/${id}`);
  }

  deleteOrcamento(id: string): Observable<Orcamento> {
    return this.http.delete<Orcamento>(`${this.apiUrl}/delete/${id}`);
  }
}
