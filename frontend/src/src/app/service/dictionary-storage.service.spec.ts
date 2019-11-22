import { TestBed } from '@angular/core/testing';

import { DictionaryStorageService } from './dictionary-storage.service';

describe('DictionaryStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DictionaryStorageService = TestBed.get(DictionaryStorageService);
    expect(service).toBeTruthy();
  });
});
