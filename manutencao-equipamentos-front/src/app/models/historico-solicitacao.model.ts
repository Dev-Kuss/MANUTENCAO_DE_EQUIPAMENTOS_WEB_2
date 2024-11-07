export interface HistoricoSolicitacao {
  id?: number;
  dataHora: Date;
  descricao: string;
  funcionario: string;
  solicitacaoId?: number;
}

export interface HistoricoSolicitacaoRequest {
  dataHora: Date;
  descricao: string;
  funcionario: string;
  solicitacaoId: number;
} 