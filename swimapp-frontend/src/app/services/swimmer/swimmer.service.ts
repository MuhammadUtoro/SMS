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
  swimmersUrl = '/api/swimmers';
  private http: HttpClient = inject(HttpClient);

  createSwimmer(dto: CreateSwimmerDto) {
    return this.http.post<CreateSwimmerDto>(this.swimmersUrl, dto);
  }

  getSwimmersList(): Observable<SwimmerSummaryDto[]> {
    return this.http.get<SwimmerSummaryDto[]>(this.swimmersUrl);
  }

  getSwimmerById(swimmerId: number): Observable<SwimmerDetailDto> {
    return this.http.get<SwimmerDetailDto>(
      `${this.swimmersUrl}/${swimmerId}`,
    );
  }

  updateSwimmerInfo(swimmerId: number, dto: UpdateSwimmerInfoDto) {
    return this.http.put<SwimmerDetailDto>(
      `${this.swimmersUrl}/${swimmerId}`, dto
    );
  }

  updateSwimmerCourse(swimmerId: number, dto: UpdateSwimmerCourseDto) {
    return this.http.patch<SwimmerDetailDto>(
      `${this.swimmersUrl}/${swimmerId}/course`, dto
    );
  }

  updateSwimmerLevel(swimmerId: number, dto: UpdateSwimmerLevelDto) {
    return this.http.patch<SwimmerDetailDto>(
      `${this.swimmersUrl}/${swimmerId}/level`, dto
    );
  }

  deleteSwimmer(swimmerId: number) {
    return this.http.delete(
      `${this.swimmersUrl}/${swimmerId}`
    );
  }
}
