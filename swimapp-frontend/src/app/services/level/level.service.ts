import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { CreateLevelDto } from '../../interfaces/create-level-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LevelService {
  getLevelsList = 'http://localhost:8080/levels';
  getLevelByIdUrl = 'http://localhost:8080/levels';
  createLevelUrl = 'http://localhost:8080/levels'
  private http: HttpClient = inject(HttpClient);

  getAllLevels() {
    return this.http.get<LevelSummaryDto[]>(this.getLevelsList);
  }

  createLevel(dto: CreateLevelDto) {
    return this.http.post<CreateLevelDto>(this.createLevelUrl, dto);
  }

  getLevelById(levelId: number): Observable<LevelSummaryDto> {
    return this.http.get<LevelSummaryDto>(
      `${this.getLevelByIdUrl}/${levelId}`,
    );
  }
}
