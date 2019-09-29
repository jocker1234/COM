import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseAbstractEditComponent } from './case-abstract-edit.component';

describe('CaseAbstractEditComponent', () => {
  let component: CaseAbstractEditComponent;
  let fixture: ComponentFixture<CaseAbstractEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaseAbstractEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaseAbstractEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
