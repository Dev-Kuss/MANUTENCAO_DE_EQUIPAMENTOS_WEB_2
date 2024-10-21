export interface Solicitacao {
    dataHora: Date;
    descricaoEquipamento: string;
    estado: string;
    nomeCliente?: string;
    precoOrcado?: number;
    historico?: Historico[];
    dataPagamento?: Date;
    
    id?: number;
    destinoRedirecionamento?: string;
    dataHoraFinalizacao?: Date;
}

export interface Historico {
    dataHora: Date;
    descricao: string;
    funcionario: string;
}
