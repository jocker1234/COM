import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractsTableComponent } from './abstracts-table.component';

describe('AbstractsTableComponent', () => {
  let component: AbstractsTableComponent;
  let fixture: ComponentFixture<AbstractsTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AbstractsTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbstractsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
