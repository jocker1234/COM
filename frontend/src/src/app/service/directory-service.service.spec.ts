import { TestBed } from '@angular/core/testing';

import { DirectoryServiceService } from './directory-service.service';

describe('DirectoryServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DirectoryServiceService = TestBed.get(DirectoryServiceService);
    expect(service).toBeTruthy();
  });
});
