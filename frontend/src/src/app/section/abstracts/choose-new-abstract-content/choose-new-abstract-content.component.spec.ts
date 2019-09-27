import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseNewAbstractContentComponent } from './choose-new-abstract-content.component';

describe('ChooseNewAbstractContentComponent', () => {
  let component: ChooseNewAbstractContentComponent;
  let fixture: ComponentFixture<ChooseNewAbstractContentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseNewAbstractContentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseNewAbstractContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
