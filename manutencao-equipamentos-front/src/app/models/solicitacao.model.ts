export interface Solicitacao {
    idSolicitacao?: number;
    dataHora: Date;
    descricaoEquipamento: string;
    descricaoDefeito?: string;
    estado: string;
    dataPagamento?: Date;
    dataHoraFinalizacao?: Date;
    
    categoria: {
        idCategoria: number;
        nome: string;
    };
    
    cliente?: {
        idCliente: number;
        nome: string;
    };
    
    responsavel?: {
        idFuncionario: number;
        nome: string;
    };
    
    historicos?: HistoricoSolicitacao[];
    orcamentos?: Orcamento[];
}

export interface HistoricoSolicitacao {
    idHistorico?: number;
    dataHora: Date;
    descricao: string;
    funcionario: {
        idFuncionario: number;
        nome: string;
    };
}

export interface Orcamento {
    idOrcamento?: number;
    valor: number;
    descricao: string;
    dataHora: Date;
    aprovado?: boolean;
}
