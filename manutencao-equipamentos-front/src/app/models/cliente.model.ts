export interface Cliente {
    id: string; // UUID em formato de string
    userId: string; // ID do usuário associado
    cpf: string;
    email: string;
    nome: string;
    telefone: string;
    cep: string;
    logradouro: string;
    numero: string;
    complemento: string;
    bairro: string;
    cidade: string;
    estado: string;
}