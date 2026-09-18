import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateSwimmerDto } from '../../interfaces/create-swimmer-dto';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { UpdateSwimmerInfoDto } from '../../interfaces/update-swimmer-info-dto';
import { UpdateSwimmerCourseDto } from '../../interfaces/update-swimmer-course-dto';
import { UpdateSwimmerLevelDto } from '../../interfaces/update-swimmer-level-dto';
import { SwimmerDetailDto } from '../../interfaces/swimmer-detail-dto';


@Injectable({
  providedIn: 'root',
})
export class SwimmerService {
  createSwimmerUrl = 'http://localhost:8080/swimmers';
  getSwimmersListUrl = 'http://localhost:8080/swimmers';
  getSwimmerByIdUrl = 'http://localhost:8080/swimmers';
  updateSwimmerInfoUrl = 'http://localhost:8080/swimmers';
  updateSwimmerCourseUrl = 'http://localhost:8080/swimmers';
  deleteSwimmerUrl = 'http://localhost:8080/swimmers';
  private http: HttpClient = inject(HttpClient);

  createSwimmer(dto: CreateSwimmerDto) {
    return this.http.post<CreateSwimmerDto>(this.createSwimmerUrl, dto);
  }

  getSwimmersList(): Observable<SwimmerSummaryDto[]> {
    return this.http.get<SwimmerSummaryDto[]>(this.getSwimmersListUrl);
  }

  getSwimmerById(swimmerId: number): Observable<SwimmerDetailDto> {
    return this.http.get<SwimmerDetailDto>(
      `${this.getSwimmerByIdUrl}/${swimmerId}`,
    );
  }

  updateSwimmerInfo(swimmerId: number, dto: UpdateSwimmerInfoDto) {
    return this.http.put<SwimmerDetailDto>(
      `${this.updateSwimmerInfoUrl}/${swimmerId}`, dto
    );
  }

  updateSwimmerCourse(swimmerId: number, dto: UpdateSwimmerCourseDto) {
    return this.http.patch<SwimmerDetailDto>(
      `${this.updateSwimmerCourseUrl}/${swimmerId}/course`, dto
    );
  }

  updateSwimmerLevel(swimmerId: number, dto: UpdateSwimmerLevelDto) {
    return this.http.patch<SwimmerDetailDto>(
      `${this.updateSwimmerCourseUrl}/${swimmerId}/level`, dto
    );
  }

  deleteSwimmer(swimmerId: number) {
    return this.http.delete(
      `${this.deleteSwimmerUrl}/${swimmerId}`
    );
  }
}
