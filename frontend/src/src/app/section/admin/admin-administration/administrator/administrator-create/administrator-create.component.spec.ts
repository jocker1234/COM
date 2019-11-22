import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministratorCreateComponent } from './administrator-create.component';

describe('AdministratorCreateComponent', () => {
  let component: AdministratorCreateComponent;
  let fixture: ComponentFixture<AdministratorCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdministratorCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdministratorCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
