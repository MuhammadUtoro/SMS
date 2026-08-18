import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SwimmerRegistration } from './swimmer-registration';

describe('SwimmerRegistration', () => {
  let component: SwimmerRegistration;
  let fixture: ComponentFixture<SwimmerRegistration>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SwimmerRegistration]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SwimmerRegistration);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
