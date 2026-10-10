import { Injectable, inject } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { CreateCourseDto } from '../../interfaces/create-course-dto';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { UpdateCourseInfoDto } from '../../interfaces/update-course-info-dto';
import { Observable } from 'rxjs';
import { UpdateCourseTrainerDto } from '../../interfaces/update-course-trainer-dto';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  coursesUrl = '/api/courses';
  private http: HttpClient = inject(HttpClient);

  createCourse(dto: CreateCourseDto) {
    return this.http.post<CreateCourseDto>(this.coursesUrl, dto);
  }

  getAllCourses() {
    return this.http.get<CourseSummaryDto[]>(this.coursesUrl);
  }

  getCourseById(courseId: number): Observable<CourseSummaryDto> {
    return this.http.get<CourseSummaryDto>(
      `${this.coursesUrl}/${courseId}`,
    );
  }

  updateCourseInfo(courseId: number, dto: UpdateCourseInfoDto) {
    return this.http.put<CourseSummaryDto>(
      `${this.coursesUrl}/${courseId}`, dto
    );
  }

  updateCourseTrainer(courseId: number, dto: UpdateCourseTrainerDto) {
    return this.http.patch<CourseSummaryDto>(
      `${this.coursesUrl}/${courseId}/trainer`, dto
    )
  }

  deleteCourse(courseId: number) {
    return this.http.delete(
      `${this.coursesUrl}/${courseId}`
    );
  }
}
