import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseAbstractCreateComponent } from './case-abstract-create.component';

describe('CaseAbstractCreateComponent', () => {
  let component: CaseAbstractCreateComponent;
  let fixture: ComponentFixture<CaseAbstractCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaseAbstractCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaseAbstractCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
