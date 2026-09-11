import { TestBed } from '@angular/core/testing';

import { LevelRequirementService } from './level-requirement.service';

describe('LevelRequirementService', () => {
  let service: LevelRequirementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LevelRequirementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
