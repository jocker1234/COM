import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckMultiUserToMailComponent } from './check-multi-user-to-mail.component';

describe('CheckMultiUserToMailComponent', () => {
  let component: CheckMultiUserToMailComponent;
  let fixture: ComponentFixture<CheckMultiUserToMailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckMultiUserToMailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckMultiUserToMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
