import { Orcamento } from "./orcamento.model";
import { HistoricoSolicitacao } from "./historico-solicitacao.model";

export interface Solicitacao {
    idSolicitacao?: number;
    dataHora: Date;
    descricaoEquipamento: string;
    descricaoDefeito?: string;
    estado: string;
    dataPagamento?: Date;
    dataHoraFinalizacao?: Date; 
    idCategoria: number;
    idCliente: string;
    idResponsavel?: string;
    historicos?: HistoricoSolicitacao[];
    orcamentos?: Orcamento[];
}