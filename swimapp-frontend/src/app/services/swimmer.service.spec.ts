import { TestBed } from '@angular/core/testing';

import { SwimmerService } from './swimmer.service';

describe('SwimmerService', () => {
  let service: SwimmerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SwimmerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
