import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignLevel } from './assign-level';

describe('AssignLevel', () => {
  let component: AssignLevel;
  let fixture: ComponentFixture<AssignLevel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignLevel]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignLevel);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
