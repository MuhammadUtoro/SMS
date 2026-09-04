import { Injectable, inject } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { CreateCourseDto } from '../../interfaces/create-course-dto';


@Injectable({
  providedIn: 'root',
})
export class CourseService {
  createCourseUrl = 'http://localhost:8080/courses';

  private http: HttpClient = inject(HttpClient);

  createCourse(dto: CreateCourseDto) {
    return this.http.post<CreateCourseDto>(this.createCourseUrl, dto);
  }
}
