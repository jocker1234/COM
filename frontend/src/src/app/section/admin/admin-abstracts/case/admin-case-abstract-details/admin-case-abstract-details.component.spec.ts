import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCaseAbstractDetailsComponent } from './admin-case-abstract-details.component';

describe('AdminCaseAbstractDetailsComponent', () => {
  let component: AdminCaseAbstractDetailsComponent;
  let fixture: ComponentFixture<AdminCaseAbstractDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCaseAbstractDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCaseAbstractDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
