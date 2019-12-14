import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersExportComponent } from './users-export.component';

describe('UsersExportComponent', () => {
  let component: UsersExportComponent;
  let fixture: ComponentFixture<UsersExportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersExportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
