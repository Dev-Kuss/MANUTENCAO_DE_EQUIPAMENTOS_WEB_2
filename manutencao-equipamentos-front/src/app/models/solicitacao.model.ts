export interface Solicitacao {
    dataHora: Date;
    descricaoEquipamento: string;
    estado: string;
    precoOrcado?: number;
    historico?: Historico[];
    dataPagamento?: Date;
}

export interface Historico {
    dataHora: Date;
    descricao: string;
    funcionario: string;
}
