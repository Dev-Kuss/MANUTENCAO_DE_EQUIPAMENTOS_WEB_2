export interface Solicitacao {
    dataHora: Date;
    descricaoEquipamento: string;
    estado: string;
    nomeCliente?: string;
    precoOrcado?: number;
    historico?: Historico[];
    dataPagamento?: Date;
    descricaoDefeito?: string;
    
    id?: number;
    destinoRedirecionamento?: string;
    dataHoraFinalizacao?: Date;

    categoria?: string;
    valor?: number;
}

export interface Historico {
    dataHora: Date;
    descricao: string;
    funcionario: string;
}
