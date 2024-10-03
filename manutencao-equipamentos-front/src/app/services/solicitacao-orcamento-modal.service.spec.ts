import { TestBed } from '@angular/core/testing';

import { SolicitacaoOrcamentoModalService } from './solicitacao-orcamento-modal.service';

describe('SolicitacaoOrcamentoModalService', () => {
  let service: SolicitacaoOrcamentoModalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitacaoOrcamentoModalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
