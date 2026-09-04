import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CourseService } from '../../services/course/course.service';

@Component({
  selector: 'app-course-form',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './course-form.html',
  styleUrl: './course-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CourseForm {
  private courseService: CourseService = inject(CourseService);
  private router: Router = inject(Router);

  days: string[] = [
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday'
  ]

  form: FormGroup = new FormGroup({
    levelId: new FormControl(''),
    trainerId: new FormControl(''),
    courseName: new FormControl(''),
    courseDay: new FormControl(''),
    courseTime: new FormControl(''),
  })
}
