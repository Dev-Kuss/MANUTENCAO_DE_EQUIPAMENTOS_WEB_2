import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario.model';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {
  private apiUrl = 'http://localhost:8080/funcionario';

  constructor(private http: HttpClient) {}

  getAllFuncionarios(): Observable<Funcionario[]> {
    return this.http.get<Funcionario[]>(`${this.apiUrl}/read-all`);
  }

  getFuncionarioById(id: string): Observable<Funcionario> {
    return this.http.get<Funcionario>(`${this.apiUrl}/read/${id}`);
  }

  createFuncionario(data: any): Observable<Funcionario> {
    return this.http.post<Funcionario>(`${this.apiUrl}/create`, data);
  }

  updateFuncionario(id: string, data: any): Observable<Funcionario> {
    return this.http.put<Funcionario>(`${this.apiUrl}/update/${id}`, data);
  }

  deleteFuncionario(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
