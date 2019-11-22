import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DictionaryDetailsComponent } from './dictionary-details.component';

describe('DictionaryDetailsComponent', () => {
  let component: DictionaryDetailsComponent;
  let fixture: ComponentFixture<DictionaryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DictionaryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DictionaryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
