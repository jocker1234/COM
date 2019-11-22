import { TestBed } from '@angular/core/testing';

import { SortServiceService } from './sort-service.service';

describe('SortServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SortServiceService = TestBed.get(SortServiceService);
    expect(service).toBeTruthy();
  });
});
