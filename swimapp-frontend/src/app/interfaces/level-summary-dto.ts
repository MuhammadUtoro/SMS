import { LevelRequirementDto } from "./level-requirement-dto";

export interface LevelSummaryDto {
  levelId: number;
  levelName: string;
  requirements: LevelRequirementDto[];
}
