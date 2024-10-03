import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitacaoOrcamentoModalComponent } from './solicitacao-orcamento-modal.component';

describe('SolicitacaoOrcamentoModalComponent', () => {
  let component: SolicitacaoOrcamentoModalComponent;
  let fixture: ComponentFixture<SolicitacaoOrcamentoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitacaoOrcamentoModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SolicitacaoOrcamentoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
