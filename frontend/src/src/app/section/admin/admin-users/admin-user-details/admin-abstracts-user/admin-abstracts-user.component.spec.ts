import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAbstractsUserComponent } from './admin-abstracts-user.component';

describe('AdminAbstractsUserComponent', () => {
  let component: AdminAbstractsUserComponent;
  let fixture: ComponentFixture<AdminAbstractsUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAbstractsUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAbstractsUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
