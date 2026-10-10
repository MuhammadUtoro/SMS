import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { CreateLevelDto } from '../../interfaces/create-level-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LevelService {
  levelsUrl = '/api/levels';
  private http: HttpClient = inject(HttpClient);

  getAllLevels() {
    return this.http.get<LevelSummaryDto[]>(this.levelsUrl);
  }

  createLevel(dto: CreateLevelDto) {
    return this.http.post<CreateLevelDto>(this.levelsUrl, dto);
  }

  getLevelById(levelId: number): Observable<LevelSummaryDto> {
    return this.http.get<LevelSummaryDto>(
      `${this.levelsUrl}/${levelId}`,
    );
  }
}
