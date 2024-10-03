import { Injectable } from '@angular/core';

interface Solicitacao {
  dataHora: Date;
  descricaoEquipamento: string;
  estado: string;
  precoOrcado?: number;
}

@Injectable({
  providedIn: 'root',
})
export class SolicitacaoService {
  constructor() {}

  aprovarSolicitacao(solicitacao: Solicitacao) {
    solicitacao.estado = 'ARRUMADA';
    console.log('Serviço aprovado:', solicitacao);
    // Possível chamada ao backend
    // return this.http.put('/api/solicitacoes/' + solicitacao.id, solicitacao);
  }

  rejeitarSolicitacao(solicitacao: Solicitacao) {
    solicitacao.estado = 'REJEITADA';
    console.log('Serviço rejeitado:', solicitacao);
    // Possível chamada ao backend
    // return this.http.put('/api/solicitacoes/' + solicitacao.id, solicitacao);
  }
}
