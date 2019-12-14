import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldSuccessfullDisplayComponent } from './field-successfull-display.component';

describe('FieldSuccessfullDisplayComponent', () => {
  let component: FieldSuccessfullDisplayComponent;
  let fixture: ComponentFixture<FieldSuccessfullDisplayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FieldSuccessfullDisplayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldSuccessfullDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
