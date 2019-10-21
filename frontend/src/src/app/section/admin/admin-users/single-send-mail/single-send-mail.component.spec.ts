import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleSendMailComponent } from './single-send-mail.component';

describe('SingleSendMailComponent', () => {
  let component: SingleSendMailComponent;
  let fixture: ComponentFixture<SingleSendMailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleSendMailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleSendMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
