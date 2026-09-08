import { Component, ChangeDetectionStrategy, inject, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CourseService } from '../../services/course/course.service';
import { TrainerService } from '../../services/trainer/trainer.service';
import { provideNativeDateAdapter } from '@angular/material/core';
import { TrainerSummaryDto } from '../../interfaces/trainer-summary-dto';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { LevelService } from '../../services/level/level.service';

@Component({
  selector: 'app-course-form',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatTimepickerModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './course-form.html',
  styleUrl: './course-form.css',
  providers: [provideNativeDateAdapter()],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CourseForm implements OnInit{
  private courseService: CourseService = inject(CourseService);
  private router: Router = inject(Router);
  private trainerService: TrainerService = inject(TrainerService);
  private levelService: LevelService = inject(LevelService);

  trainers: TrainerSummaryDto[] = [];
  levels: LevelSummaryDto[] = [];

  form: FormGroup = new FormGroup({
    levelId: new FormControl(''),
    trainerId: new FormControl(''),
    courseName: new FormControl(''),
    courseDay: new FormControl(''),
    courseTime: new FormControl(''),
  })

  ngOnInit() {
    this.trainerService.getAllTrainers().subscribe({
      next: (trainers) => {
        this.trainers = trainers;
      }
    });
    this.levelService.getAllLevels().subscribe({
      next: (levels) => {
        this.levels = levels;
      }
    });
  }

  createCourse() {
    const time = this.form.value.courseTime;

    const request = {
      ...this.form.value,
      courseTime: time
      ? `${String(time.getHours()).padStart(2, '0')}:${String(time.getMinutes()).padStart(2, '0')}:00` : null
    }
    this.courseService.createCourse(request).subscribe({
      next: (response) => {
        console.log('Success', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.log('Failed to create course', error);
      }
    });
  }

  days: string[] = [
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday'
  ]

  courses: string[] = [
    'Beginner',
    'Intermediate',
    'Advance',
    'Competitive'
  ]

}
