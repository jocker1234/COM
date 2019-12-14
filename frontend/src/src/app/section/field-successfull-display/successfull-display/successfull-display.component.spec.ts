import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfullDisplayComponent } from './successfull-display.component';

describe('SuccessfullDisplayComponent', () => {
  let component: SuccessfullDisplayComponent;
  let fixture: ComponentFixture<SuccessfullDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfullDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfullDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
