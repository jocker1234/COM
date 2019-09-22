import { TestBed } from '@angular/core/testing';
import {AbstractsService} from "./abstracts.service";

describe('AbstractsServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AbstractsService = TestBed.get(AbstractsService);
    expect(service).toBeTruthy();
  });
});
