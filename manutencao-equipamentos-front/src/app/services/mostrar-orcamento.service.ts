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

export class MostrarOrcamentoService {
  constructor() {}

  aprovarSolicitacao(solicitacao: Solicitacao) {
    solicitacao.estado = 'ARRUMADA';
    console.log('Serviço aprovado:', solicitacao);
    // TODO: Chamada ao back-end
  }

  rejeitarSolicitacao(solicitacao: Solicitacao) {
    solicitacao.estado = 'REJEITADA';
    console.log('Serviço rejeitado:', solicitacao);
    // TODO: Chamada ao back-end
  }
}
