import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractActionButtonsComponent } from './abstract-action-buttons.component';

describe('AbstractActionButtonsComponent', () => {
  let component: AbstractActionButtonsComponent;
  let fixture: ComponentFixture<AbstractActionButtonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AbstractActionButtonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbstractActionButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
