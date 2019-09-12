import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaseAbstractItemsDetailsComponent } from './case-abstract-items-details.component';

describe('CaseAbstractItemsDetailsComponent', () => {
  let component: CaseAbstractItemsDetailsComponent;
  let fixture: ComponentFixture<CaseAbstractItemsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaseAbstractItemsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaseAbstractItemsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
