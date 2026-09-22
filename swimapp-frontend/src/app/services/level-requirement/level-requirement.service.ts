import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelRequirementDto } from '../../interfaces/level-requirement-dto';
import { CreateLevelRequirementDto } from '../../interfaces/create-level-requirement-dto';
import { LevelRequirementSummaryDto } from '../../interfaces/level-requirement-summary-dto';


@Injectable({
  providedIn: 'root',
})
export class LevelRequirementService {
  createLevelRequirementUrl = 'http://localhost:8080/level-requirements';
  getRequirementsList = 'http://localhost:8080/level-requirements';
  getRequirementByIdUrl = 'http://localhost:8080/level-requirements';
  deleteRequirementUrl = 'http://localhost:8080/level-requirements';
  private http: HttpClient = inject(HttpClient);

  getAllRequirements() {
    return this.http.get<LevelRequirementDto[]>(this.getRequirementsList);
  }

  getRequirementById(requirementId: number) {
    return this.http.get<LevelRequirementSummaryDto>(
      `${this.getRequirementByIdUrl}/${requirementId}`
    );
  }

  createRequirement(dto: CreateLevelRequirementDto) {
    return this.http.post<LevelRequirementSummaryDto>(this.createLevelRequirementUrl, dto);
  }

  deleteRequirement(requirementId: number) {
    return this.http.delete(
      `${this.deleteRequirementUrl}/${requirementId}`
    );
  }
}
