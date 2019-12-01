import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiSendMailComponent } from './multi-send-mail.component';

describe('MultiSendMailComponent', () => {
  let component: MultiSendMailComponent;
  let fixture: ComponentFixture<MultiSendMailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MultiSendMailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiSendMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
