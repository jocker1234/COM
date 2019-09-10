import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchAbstractCreateComponent } from './research-abstract-create.component';

describe('ResearchAbstractCreateComponent', () => {
  let component: ResearchAbstractCreateComponent;
  let fixture: ComponentFixture<ResearchAbstractCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResearchAbstractCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchAbstractCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
