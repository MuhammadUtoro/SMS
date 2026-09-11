import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelRequirementDto } from '../../interfaces/level-requirement-dto';

@Injectable({
  providedIn: 'root',
})
export class LevelRequirementService {
  createLevelRequirementUrl = 'http://localhost:8080/level-requirements';
  getRequirementsList = 'http://localhost:8080/level-requirements'

  private http: HttpClient = inject(HttpClient);

  getAllRequirements() {
    return this.http.get<LevelRequirementDto[]>(this.getRequirementsList);
  }
}
