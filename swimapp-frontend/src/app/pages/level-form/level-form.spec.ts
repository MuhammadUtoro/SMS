import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LevelForm } from './level-form';

describe('LevelForm', () => {
  let component: LevelForm;
  let fixture: ComponentFixture<LevelForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LevelForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LevelForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
