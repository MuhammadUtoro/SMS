import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignTrainer } from './assign-trainer';

describe('AssignTrainer', () => {
  let component: AssignTrainer;
  let fixture: ComponentFixture<AssignTrainer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignTrainer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignTrainer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
