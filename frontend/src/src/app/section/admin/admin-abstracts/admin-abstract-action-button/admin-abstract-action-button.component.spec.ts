import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAbstractActionButtonComponent } from './admin-abstract-action-button.component';

describe('AdminAbstractActionButtonComponent', () => {
  let component: AdminAbstractActionButtonComponent;
  let fixture: ComponentFixture<AdminAbstractActionButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAbstractActionButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAbstractActionButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
