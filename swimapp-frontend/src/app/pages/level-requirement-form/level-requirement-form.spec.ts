import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LevelRequirementForm } from './level-requirement-form';

describe('LevelRequirementForm', () => {
  let component: LevelRequirementForm;
  let fixture: ComponentFixture<LevelRequirementForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LevelRequirementForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LevelRequirementForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
