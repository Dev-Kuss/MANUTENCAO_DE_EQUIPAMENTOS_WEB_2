import { Orcamento } from "./orcamento.model";
import { HistoricoSolicitacao } from "./historico-solicitacao.model";
import { Cliente } from "./cliente.model";
import { Funcionario } from './funcionario.model';
import { Categoria } from './categorias.model';

export interface Solicitacao {
    idSolicitacao: number;
    dataHora: Date;
    descricaoEquipamento: string;
    descricaoDefeito?: string;
    estado: string;
    dataPagamento?: string | Date;
    dataHoraFinalizacao?: string | Date;
    idCategoria: number;
    categoria: Categoria;
    idCliente: string;
    cliente: Cliente; 
    idResponsavel?: string;
    historicos?: HistoricoSolicitacao[];
    orcamentos?: Orcamento[];
    orientacoesCliente?: string;
    responsavel?: Funcionario;
}
