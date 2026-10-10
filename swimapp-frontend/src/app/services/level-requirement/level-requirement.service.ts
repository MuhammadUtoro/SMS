import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LevelRequirementDto } from '../../interfaces/level-requirement-dto';
import { CreateLevelRequirementDto } from '../../interfaces/create-level-requirement-dto';
import { LevelRequirementSummaryDto } from '../../interfaces/level-requirement-summary-dto';


@Injectable({
  providedIn: 'root',
})
export class LevelRequirementService {
  levelRequirementsUrl = '/api/level-requirements';
  private http: HttpClient = inject(HttpClient);

  getAllRequirements() {
    return this.http.get<LevelRequirementDto[]>(this.levelRequirementsUrl);
  }

  getRequirementById(requirementId: number) {
    return this.http.get<LevelRequirementSummaryDto>(
      `${this.levelRequirementsUrl}/${requirementId}`
    );
  }

  createRequirement(dto: CreateLevelRequirementDto) {
    return this.http.post<LevelRequirementSummaryDto>(this.levelRequirementsUrl, dto);
  }

  deleteRequirement(requirementId: number) {
    return this.http.delete(
      `${this.levelRequirementsUrl}/${requirementId}`
    );
  }
}
