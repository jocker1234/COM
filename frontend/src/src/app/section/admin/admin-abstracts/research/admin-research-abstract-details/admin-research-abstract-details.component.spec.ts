import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminResearchAbstractDetailsComponent } from './admin-research-abstract-details.component';

describe('AdminResearchAbstractDetailsComponent', () => {
  let component: AdminResearchAbstractDetailsComponent;
  let fixture: ComponentFixture<AdminResearchAbstractDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminResearchAbstractDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminResearchAbstractDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
