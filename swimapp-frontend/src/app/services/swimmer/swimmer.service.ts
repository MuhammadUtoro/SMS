import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateSwimmerDto } from '../../interfaces/create-swimmer-dto';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';

@Injectable({
  providedIn: 'root',
})
export class SwimmerService {
  createSwimmerUrl = 'http://localhost:8080/swimmers';
  getSwimmersListUrl = 'http://localhost:8080/swimmers';
  private http: HttpClient = inject(HttpClient);

  createSwimmer(dto: CreateSwimmerDto) {
    return this.http.post<CreateSwimmerDto>(this.createSwimmerUrl, dto);
  }

  getSwimmersList(): Observable<SwimmerSummaryDto[]> {
    return this.http.get<SwimmerSummaryDto[]>(this.getSwimmersListUrl);
  }
}
