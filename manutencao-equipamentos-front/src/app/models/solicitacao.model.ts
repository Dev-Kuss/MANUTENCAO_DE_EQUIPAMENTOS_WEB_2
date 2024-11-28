export interface Solicitacao {
    idSolicitacao?: number;
    dataHora: Date;
    descricaoEquipamento: string;
    descricaoDefeito?: string;
    estado: string;
    dataPagamento?: Date;
    dataHoraFinalizacao?: Date;
    
    
    idCategoria: number;
    
    idCliente: number;
    
    idResponsavel?: number;
    
    historicos?: HistoricoSolicitacao[];
    orcamentos?: Orcamento[];
}

export interface HistoricoSolicitacao {
    idHistorico?: number;
    dataHora: Date;
    descricao: string;
    destinoRedirecionamento?: string;
    idFuncionario: number;
}

export interface Orcamento {
    idOrcamento?: number;
    valor: number;
    descricao: string;
    dataHora: Date;
    aprovado?: boolean;
}
