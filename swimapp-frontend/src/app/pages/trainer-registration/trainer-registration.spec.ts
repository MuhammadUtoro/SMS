import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerRegistration } from './trainer-registration';

describe('TrainerRegistration', () => {
  let component: TrainerRegistration;
  let fixture: ComponentFixture<TrainerRegistration>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TrainerRegistration]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrainerRegistration);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
