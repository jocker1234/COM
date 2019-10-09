import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAbstractsComponent } from './admin-abstracts.component';

describe('AdminAbstractsComponent', () => {
  let component: AdminAbstractsComponent;
  let fixture: ComponentFixture<AdminAbstractsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAbstractsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAbstractsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
