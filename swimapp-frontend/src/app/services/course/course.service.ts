import { Injectable, inject } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { CreateCourseDto } from '../../interfaces/create-course-dto';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { UpdateCourseInfoDto } from '../../interfaces/update-course-info-dto';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class CourseService {
  createCourseUrl = 'http://localhost:8080/courses';
  getCoursesListUrl = 'http://localhost:8080/courses';
  getCourseByIdUrl = 'http://localhost:8080/courses';
  updateCourseLevelUrl = 'http://localhost:8080/courses';
  updateCourseInfoUrl = 'http://localhost:8080/courses';
  deleteCourseUrl = 'http://localhost:8080/courses';
  private http: HttpClient = inject(HttpClient);

  createCourse(dto: CreateCourseDto) {
    return this.http.post<CreateCourseDto>(this.createCourseUrl, dto);
  }

  getAllCourses() {
    return this.http.get<CourseSummaryDto[]>(this.getCoursesListUrl);
  }

  getCourseById(courseId: number): Observable<CourseSummaryDto> {
    return this.http.get<CourseSummaryDto>(
      `${this.getCourseByIdUrl}/${courseId}`,
    );
  }

  updateCourseInfo(courseId: number, dto: UpdateCourseInfoDto) {
    return this.http.put<CourseSummaryDto>(
      `${this.updateCourseInfoUrl}/${courseId}`, dto
    );
  }

  deleteCourse(courseId: number) {
    return this.http.delete(
      `${this.deleteCourseUrl}/${courseId}`
    );
  }
}
