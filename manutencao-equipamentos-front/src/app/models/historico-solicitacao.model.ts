export interface HistoricoSolicitacao {
    idHistorico?: number;
    dataHora: Date;
    descricao: string;
    destinoRedirecionamento?: string;
    idFuncionario: number;
    nomeFuncionario: string;
}