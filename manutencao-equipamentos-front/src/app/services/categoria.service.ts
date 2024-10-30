import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {catchError, map, Observable, throwError} from 'rxjs';
import { Categoria } from '../models/categorias.model';

@Injectable({
  providedIn: 'root'  // Em Standalone, o serviço é fornecido globalmente
})
export class CategoriaService {
  private apiUrl = 'http://localhost:8080/categoria-equipamento'; // Defina o URL do back-end

  constructor(private http: HttpClient) {}

  // Métodos CRUD para integração com o back-end
  getCategorias(): Observable<Categoria[]> {
    return this.http.get<any[]>(`${this.apiUrl}/read-all`).pipe(
      map(data => data.map(item => ({ id: item.id, nome_categoria: item.nome_categoria, ativo: item.ativo })))
    );
  }

  createCategoria(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(`${this.apiUrl}/create`, categoria);
  }

  updateCategoria(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.apiUrl}/update/${id}`, categoria);
  }

  deleteCategoria(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 409) {  // Conflito, indicando violação de integridade
          alert(error.error);  // Exibe a mensagem do backend no frontend
        }
        return throwError(() => new Error('Erro ao excluir categoria.'));
      })
    );
  }
}
