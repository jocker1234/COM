import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchAbstractItemsDetailsComponent } from './research-abstract-items-details.component';

describe('ResearchAbstractItemsDetailsComponent', () => {
  let component: ResearchAbstractItemsDetailsComponent;
  let fixture: ComponentFixture<ResearchAbstractItemsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResearchAbstractItemsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchAbstractItemsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
