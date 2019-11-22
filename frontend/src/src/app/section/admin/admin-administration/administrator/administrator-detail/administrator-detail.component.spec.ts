import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministratorDetailComponent } from './administrator-detail.component';

describe('AdministratorDetailComponent', () => {
  let component: AdministratorDetailComponent;
  let fixture: ComponentFixture<AdministratorDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministratorDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministratorDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
