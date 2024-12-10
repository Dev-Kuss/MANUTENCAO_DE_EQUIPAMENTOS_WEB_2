export interface HistoricoSolicitacao {
    idHistorico?: number;
    dataHora: Date;
    descricao: string;
    destinoRedirecionamento?: string;
    idFuncionario: string;
    nomeFuncionario: string;
}