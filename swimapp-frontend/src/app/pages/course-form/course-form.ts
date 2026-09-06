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

  trainers: TrainerSummaryDto[] = [];

  ngOnInit() {
    this.trainerService.getAllTrainers().subscribe({
      next: (trainers) => {
        this.trainers = trainers;
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

  form: FormGroup = new FormGroup({
    levelId: new FormControl(''),
    trainerId: new FormControl(''),
    courseName: new FormControl(''),
    courseDay: new FormControl(''),
    courseTime: new FormControl(''),
  })
}
