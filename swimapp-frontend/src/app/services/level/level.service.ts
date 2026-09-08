import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';

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
}
