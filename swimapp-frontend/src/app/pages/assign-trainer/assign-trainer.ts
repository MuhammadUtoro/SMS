import { Component, ChangeDetectionStrategy, inject, signal, OnInit, afterNextRender } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CourseService } from '../../services/course/course.service';
import { TrainerService } from '../../services/trainer/trainer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { TrainerSummaryDto } from '../../interfaces/trainer-summary-dto';
import { UpdateCourseTrainerDto } from '../../interfaces/update-course-trainer-dto';


@Component({
  selector: 'app-assign-trainer',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './assign-trainer.html',
  styleUrl: './assign-trainer.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignTrainer implements OnInit{
  private courseService: CourseService = inject(CourseService);
  private trainerService: TrainerService = inject(TrainerService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private router: Router = inject(Router);

  course = signal<CourseSummaryDto | undefined>(undefined);
  trainers = signal<TrainerSummaryDto[]>([]);

  form: FormGroup = new FormGroup({
    trainerId: new FormControl<number | null>(null),
  });

  ngOnInit(): void {
    const courseId = Number(
      this.route.snapshot.paramMap.get('courseId')
    );
    this.loadCourseSummary(courseId);

    this.trainerService.getAllTrainers().subscribe({
      next: (trainers) => {
        this.trainers.set(trainers);
      },
      error: (error) => {
        console.log("Failed to load trainers!", error);
      }
    });
  }

  loadCourseSummary(courseId: number) {
    this.courseService.getCourseById(courseId).subscribe({
      next: (course) => {
        this.course.set(course);
      },
      error: (error) => {
        console.log("Failed to load course!", error);
      }
    });
  }

  assignTrainer(courseId: number): void {
    const trainerId = this.form.value.trainerId;
    const dto: UpdateCourseTrainerDto = {
      trainerId: trainerId,
    }

    this.courseService.updateCourseTrainer(courseId, dto).subscribe({
      next: (updatedCourse) => {
        this.router.navigate(['/courses', courseId]);
        this.course.set(updatedCourse);
      },
      error: (error) => {
        console.log("Failed to update trainer!", error);
      },
    });
  }
}
