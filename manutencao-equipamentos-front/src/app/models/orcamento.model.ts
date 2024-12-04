
export interface Orcamento {
    idOrcamento?: number;
    solicitacaoId: number;
    funcionarioId: string | null;
    valor: number;
    descricao: string;
    dataHora: Date;
    aprovado?: boolean;
}
