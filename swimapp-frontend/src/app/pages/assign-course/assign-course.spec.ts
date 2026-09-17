import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignCourse } from './assign-course';

describe('AssignCourse', () => {
  let component: AssignCourse;
  let fixture: ComponentFixture<AssignCourse>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignCourse]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignCourse);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
