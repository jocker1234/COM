import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchAbstractEditComponent } from './research-abstract-edit.component';

describe('ResearchAbstractEditComponent', () => {
  let component: ResearchAbstractEditComponent;
  let fixture: ComponentFixture<ResearchAbstractEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResearchAbstractEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchAbstractEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
