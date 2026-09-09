import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { CreateLevelDto } from '../../interfaces/create-level-dto';

@Injectable({
  providedIn: 'root',
})
export class LevelService {
  getLevelsList = 'http://localhost:8080/levels';
  createLevelUrl = 'http://localhost:8080/levels'
  private http: HttpClient = inject(HttpClient);

  getAllLevels() {
    return this.http.get<LevelSummaryDto[]>(this.getLevelsList);
  }

  createLevel(dto: CreateLevelDto) {
    return this.http.post<CreateLevelDto>(this.createLevelUrl, dto);
  }
}
