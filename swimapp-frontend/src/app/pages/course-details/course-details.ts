import { Component, signal, OnInit, inject } from '@angular/core';
import { CourseService } from '../../services/course/course.service';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { Swimmer } from '../../components/swimmer/swimmer';

@Component({
  selector: 'app-course-details',
  imports: [RouterLink, MatButtonModule],
  templateUrl: './course-details.html',
  styleUrl: './course-details.css',
})
export class CourseDetails implements OnInit{
  private courseService: CourseService = inject(CourseService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);

  courseDetails = signal<CourseSummaryDto | undefined>(undefined);
  ngOnInit(): void {
    const courseId = Number(
      this.route.snapshot.paramMap.get('courseId')
    );
    this.getCourseDetails(courseId);
  }
  getCourseDetails(courseId: number): void {
    this.courseService.getCourseById(courseId).subscribe({
      next: (course) => {
        this.courseDetails.set(course);
      },
      error: (error) => {
        console.log("Failed to load course", error);
      }
    });
  }
  deleteCourse(courseId: number): void {
    this.courseService.deleteCourse(courseId).subscribe({
      next: () => {
        console.log("Successfully deleted!");
        this.router.navigate(['/course-list']);
      },
      error: (error) => {
        console.log("Failed to delete!", error);
      }
    });
  }
}
