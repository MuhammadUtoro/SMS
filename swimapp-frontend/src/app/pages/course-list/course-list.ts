import { Component, inject, signal, OnInit } from '@angular/core';
import { CourseService } from '../../services/course/course.service';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';

@Component({
  selector: 'app-course-list',
  imports: [],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css',
})
export class CourseList implements OnInit{
  private courseService: CourseService = inject(CourseService);

  courses = signal<CourseSummaryDto[]>([]);

  ngOnInit(): void {
      this.getCoursesList();
  }

  getCoursesList(){
    this.courseService.getAllCourses().subscribe({
      next: (courses) => {
        this.courses.set(courses);
      },
      error: error => {
        console.log("Failed to load courses!", error);
      }
    });
  }
}
