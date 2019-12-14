import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractsExportComponent } from './abstracts-export.component';

describe('AbstractsExportComponent', () => {
  let component: AbstractsExportComponent;
  let fixture: ComponentFixture<AbstractsExportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AbstractsExportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AbstractsExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
