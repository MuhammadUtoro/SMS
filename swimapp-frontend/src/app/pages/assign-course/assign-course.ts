import { Component, ChangeDetectionStrategy, inject, OnInit, signal } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { SwimmerService } from '../../services/swimmer/swimmer.service';
import { CourseService } from '../../services/course/course.service';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { SwimmerDetailDto } from '../../interfaces/swimmer-detail-dto';
import { UpdateSwimmerCourseDto } from '../../interfaces/update-swimmer-course-dto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-assign-course',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatSelectModule,
    ReactiveFormsModule,
  ],
  templateUrl: './assign-course.html',
  styleUrl: './assign-course.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignCourse implements OnInit{
  private swimmerService: SwimmerService = inject(SwimmerService);
  private courseService: CourseService = inject(CourseService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private router: Router = inject(Router);
  swimmerDetails = signal<SwimmerDetailDto | undefined>(undefined);
  courses = signal<CourseSummaryDto[]>([]);

  form: FormGroup = new FormGroup({
    courseId: new FormControl<number | null>(null),
  })

  ngOnInit(): void {
    this.courseService.getAllCourses().subscribe({
      next: (course) => {
        this.courses.set(course);
      },
      error: (error) => {
        console.log("Failed to load courses!", error);
      },
    });

    const swimmerId = Number(
      this.route.snapshot.paramMap.get('swimmerId')
    );
    this.loadSwimmerSummary(swimmerId);
  }

  loadSwimmerSummary(swimmerId: number) {
    this.swimmerService.getSwimmerById(swimmerId).subscribe({
      next: (swimmer) => {
        this.swimmerDetails.set(swimmer);
      },
      error: (error) => {
        console.log('Failed to load details', error);
      }
    });
  }

  assignCourse(swimmerId: number): void {
    const courseId = this.form.value.courseId;
    const dto: UpdateSwimmerCourseDto = {
      courseId: courseId,
    }

    this.swimmerService.updateSwimmerCourse(swimmerId, dto).subscribe({
      next: (updatedSwimmer) => {
        this.router.navigate(['/swimmers', swimmerId]);
        this.swimmerDetails.set(updatedSwimmer);
      },
      error: (error) =>{
        console.log("Failed to update course!", error);
      }
    })
  }
}
