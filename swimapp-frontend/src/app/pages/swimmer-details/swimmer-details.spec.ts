import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SwimmerDetails } from './swimmer-details';

describe('SwimmerDetails', () => {
  let component: SwimmerDetails;
  let fixture: ComponentFixture<SwimmerDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SwimmerDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SwimmerDetails);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
