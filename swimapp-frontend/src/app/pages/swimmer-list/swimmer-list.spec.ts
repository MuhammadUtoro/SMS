import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SwimmerList } from './swimmer-list';

describe('SwimmerList', () => {
  let component: SwimmerList;
  let fixture: ComponentFixture<SwimmerList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SwimmerList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SwimmerList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
