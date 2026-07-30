import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Swimmer } from './swimmer';

describe('Swimmer', () => {
  let component: Swimmer;
  let fixture: ComponentFixture<Swimmer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Swimmer]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Swimmer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
