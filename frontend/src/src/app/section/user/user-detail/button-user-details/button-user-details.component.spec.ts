import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonUserDetailsComponent } from './button-user-details.component';

describe('ButtonUserDetailsComponent', () => {
  let component: ButtonUserDetailsComponent;
  let fixture: ComponentFixture<ButtonUserDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ButtonUserDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
