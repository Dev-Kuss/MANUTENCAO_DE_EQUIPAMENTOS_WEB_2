import { Solicitacao } from "../models/solicitacao.model";

export const solicitacoes: Solicitacao[] = [
    {
      dataHora: new Date('2024-10-22T10:30:00'),
      nomeCliente: 'Lucas Oliveira',
      descricaoEquipamento: 'Monitor Samsung 24"',
      estado: 'ABERTA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      categoria: 'Monitor',
      descricaoDefeito: 'Monitor piscando e desliga sozinho após 10 minutos de uso.'
    },
    {
      dataHora: new Date('2024-10-22T08:15:00'),
      nomeCliente: 'Ana Lima',
      descricaoEquipamento: 'MacBook Pro 2021',
      estado: 'ABERTA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      categoria: 'Notebook',
      descricaoDefeito: 'MacBook travando constantemente ao abrir vários aplicativos.'
    },
    {
      dataHora: new Date('2024-10-21T13:45:00'),
      nomeCliente: 'Pedro Costa',
      descricaoEquipamento: 'Impressora HP LaserJet P1005',
      estado: 'ORÇADA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 200,
      categoria: 'Impressora',
      descricaoDefeito: 'Impressora não reconhece o cartucho de toner novo.'
    },
    {
      dataHora: new Date('2024-10-20T11:00:00'),
      nomeCliente: 'Camila Dias',
      descricaoEquipamento: 'Desktop Dell OptiPlex 3070',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 850,
      categoria: 'Desktop',
      descricaoDefeito: 'Desktop reinicia sozinho durante o uso, especialmente ao abrir programas pesados.'
    },
    {
      dataHora: new Date('2024-10-19T15:30:00'),
      nomeCliente: 'Renata Souza',
      descricaoEquipamento: 'Notebook Lenovo Ideapad 3',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 900,
      categoria: 'Notebook',
      descricaoDefeito: 'Tela do notebook está piscando e às vezes fica completamente preta.'
    },
    {
      dataHora: new Date('2024-09-15T09:15:00'),
      nomeCliente: 'Claudio Martins',
      descricaoEquipamento: 'Impressora Epson EcoTank L3150',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 450,
      categoria: 'Impressora',
      descricaoDefeito: 'Impressora não puxa papel e dá erro de papel encravado.'
    },
    {
      dataHora: new Date('2024-09-10T17:45:00'),
      nomeCliente: 'Bruna Rocha',
      descricaoEquipamento: 'Monitor LG UltraWide 34"',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 500,
      categoria: 'Monitor',
      descricaoDefeito: 'Monitor com manchas na tela e cores distorcidas.'
    },
    {
      dataHora: new Date('2024-09-05T14:30:00'),
      nomeCliente: 'Felipe Almeida',
      descricaoEquipamento: 'Desktop HP ProDesk 400 G5',
      estado: 'REJEITADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 800,
      categoria: 'Desktop',
      descricaoDefeito: 'Ventoinha do processador faz barulho excessivo e computador superaquece.'
    },
    {
      dataHora: new Date('2024-08-25T12:00:00'),
      nomeCliente: 'Fernanda Lima',
      descricaoEquipamento: 'Notebook Dell Inspiron 14',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 950,
      categoria: 'Notebook',
      descricaoDefeito: 'Teclado do notebook não responde corretamente ao digitar.'
    },
    {
      dataHora: new Date('2024-08-10T16:15:00'),
      nomeCliente: 'Gabriel Sousa',
      descricaoEquipamento: 'Impressora Brother DCP-L2540DW',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 400,
      categoria: 'Impressora',
      descricaoDefeito: 'Impressora imprime com linhas falhadas, mesmo após limpeza.'
    },
    {
      dataHora: new Date('2024-07-30T10:00:00'),
      nomeCliente: 'Rafael Figueiredo',
      descricaoEquipamento: 'Monitor Philips 27"',
      estado: 'AGUARDANDO PAGAMENTO',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 350,
      categoria: 'Monitor',
      descricaoDefeito: 'Monitor não liga após conectar ao computador.'
    },
    {
      dataHora: new Date('2024-07-15T09:30:00'),
      nomeCliente: 'Isabela Gomes',
      descricaoEquipamento: 'Desktop Lenovo ThinkCentre M720',
      estado: 'REDIRECIONADA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 750,
      categoria: 'Desktop',
      descricaoDefeito: 'Desempenho lento durante o uso e travamentos constantes.'
    },
    {
      dataHora: new Date('2024-06-30T12:00:00'),
      nomeCliente: 'Marcelo Fernandes',
      descricaoEquipamento: 'Notebook HP EliteBook',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 1200,
      categoria: 'Notebook',
      descricaoDefeito: 'Notebook não reconhece o carregador.'
    },
    {
      dataHora: new Date('2024-06-15T11:30:00'),
      nomeCliente: 'Patrícia Souza',
      descricaoEquipamento: 'Impressora Canon PIXMA',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 300,
      categoria: 'Impressora',
      descricaoDefeito: 'Impressora imprime páginas em branco.'
    },
    {
      dataHora: new Date('2024-05-25T14:45:00'),
      nomeCliente: 'Eduardo Santos',
      descricaoEquipamento: 'Desktop HP Pavilion',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 950,
      categoria: 'Desktop',
      descricaoDefeito: 'Desktop trava ao iniciar o sistema operacional.'
    },
    {
      dataHora: new Date('2024-05-10T09:15:00'),
      nomeCliente: 'Marta Oliveira',
      descricaoEquipamento: 'Monitor AOC 27"',
      estado: 'REJEITADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 400,
      categoria: 'Monitor',
      descricaoDefeito: 'Monitor com baixa resolução, mesmo ajustando as configurações.'
    },
    {
      dataHora: new Date('2024-04-28T16:00:00'),
      nomeCliente: 'Fábio Lima',
      descricaoEquipamento: 'Notebook Asus VivoBook',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 1100,
      categoria: 'Notebook',
      descricaoDefeito: 'Tela do notebook ficou preta após atualização.'
    },
    {
      dataHora: new Date('2024-04-15T09:45:00'),
      nomeCliente: 'Clara Martins',
      descricaoEquipamento: 'Impressora Epson EcoTank',
      estado: 'ORÇADA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 250,
      categoria: 'Impressora',
      descricaoDefeito: 'A impressão sai borrada, mesmo com limpeza da cabeça de impressão.'
    },
    {
      dataHora: new Date('2024-04-05T13:15:00'),
      nomeCliente: 'Daniel Alves',
      descricaoEquipamento: 'Desktop Dell Inspiron',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 700,
      categoria: 'Desktop',
      descricaoDefeito: 'Desempenho muito lento ao abrir arquivos grandes.'
    },
    {
      dataHora: new Date('2024-03-20T11:30:00'),
      nomeCliente: 'Renan Oliveira',
      descricaoEquipamento: 'Monitor Dell 24"',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 480,
      categoria: 'Monitor',
      descricaoDefeito: 'Imagens com cores distorcidas ao conectar com HDMI.'
    },
    {
      dataHora: new Date('2024-03-05T10:20:00'),
      nomeCliente: 'Carla Mendes',
      descricaoEquipamento: 'Notebook Acer Aspire',
      estado: 'AGUARDANDO PAGAMENTO',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 900,
      categoria: 'Notebook',
      descricaoDefeito: 'Teclado parou de funcionar completamente.'
    },
    {
      dataHora: new Date('2024-02-28T16:00:00'),
      nomeCliente: 'Vitor Moreira',
      descricaoEquipamento: 'Impressora HP DeskJet',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 350,
      categoria: 'Impressora',
      descricaoDefeito: 'Erro de conexão ao tentar imprimir via Wi-Fi.'
    },
    {
      dataHora: new Date('2024-02-10T09:45:00'),
      nomeCliente: 'Fernanda Silva',
      descricaoEquipamento: 'Desktop Lenovo IdeaCentre',
      estado: 'REJEITADA',
      historico: [],
      destinoRedirecionamento: 'Outro Funcionario',
      precoOrcado: 800,
      categoria: 'Desktop',
      descricaoDefeito: 'O desktop superaquece após 30 minutos de uso contínuo.'
    },
    {
      dataHora: new Date('2024-01-30T14:20:00'),
      nomeCliente: 'Juliana Lima',
      descricaoEquipamento: 'Notebook Samsung Galaxy Book',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 1000,
      categoria: 'Notebook',
      descricaoDefeito: 'Bateria não carrega além de 50%.'
    },
    {
      dataHora: new Date('2024-01-15T12:00:00'),
      nomeCliente: 'Thiago Souza',
      descricaoEquipamento: 'Monitor BenQ 24"',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 320,
      categoria: 'Monitor',
      descricaoDefeito: 'Tela fica escura após alguns minutos de uso.'
    },
    {
      dataHora: new Date('2024-01-05T09:30:00'),
      nomeCliente: 'Mariana Alves',
      descricaoEquipamento: 'Desktop Asus ROG',
      estado: 'REDIRECIONADA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 1500,
      categoria: 'Desktop',
      descricaoDefeito: 'Barulho de ventilação alto e constante, mesmo sem uso intenso.'
    },
    {
      dataHora: new Date('2023-12-15T10:45:00'),
      nomeCliente: 'Ricardo Gonçalves',
      descricaoEquipamento: 'Impressora Lexmark MS321',
      estado: 'ORÇADA',
      historico: [],
      destinoRedirecionamento: 'Maria Santos',
      precoOrcado: 400,
      categoria: 'Impressora',
      descricaoDefeito: 'Papel fica preso constantemente no alimentador.'
    },
    {
      dataHora: new Date('2023-12-05T16:00:00'),
      nomeCliente: 'Larissa Costa',
      descricaoEquipamento: 'Notebook Lenovo Yoga',
      estado: 'PAGA',
      historico: [],
      destinoRedirecionamento: 'Carlos Souza',
      precoOrcado: 1300,
      categoria: 'Notebook',
      descricaoDefeito: 'Problema na dobradiça, tela está frouxa.'
    },
    {
      dataHora: new Date('2023-11-20T11:15:00'),
      nomeCliente: 'Leonardo Pereira',
      descricaoEquipamento: 'Monitor Samsung Curvo 32"',
      estado: 'APROVADA',
      historico: [],
      destinoRedirecionamento: 'João Silva',
      precoOrcado: 650,
      categoria: 'Monitor',
      descricaoDefeito: 'Monitor desligando aleatoriamente durante o uso.'
    },
    {
      dataHora: new Date('2023-11-05T15:30:00'),
      nomeCliente: 'Amanda Ribeiro',
      descricaoEquipamento: 'Impressora Brother MFC-L2710DW',
      estado: 'FINALIZADA',
      historico: [],
      destinoRedirecionamento: 'Pedro Oliveira',
      precoOrcado: 420,
      categoria: 'Impressora',
      descricaoDefeito: 'Impressão desalinhada e falhas ao imprimir documentos grandes.'
    }
  ];
  