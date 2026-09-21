package mapper;

import dto.levelrequirement.CreateLevelRequirementDTO;
import dto.levelrequirement.CreateLevelRequirementResponseDTO;
import dto.levelrequirement.LevelRequirementDTO;
import dto.levelrequirement.LevelRequirementSummaryDTO;
import dto.levelrequirement.UpdateLevelRequirementInfoDTO;
import entity.LevelRequirement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LevelRequirementMapper {

  @Inject
  LevelRequirementMapper levelRequirementMapper;

  // CreateLevelRequirementDTO
  public LevelRequirement toCreateEntity(CreateLevelRequirementDTO levelRequirementDTO) {
    LevelRequirement levelRequirement = new LevelRequirement();
    levelRequirement.setRequirement(levelRequirementDTO.requirement());
    levelRequirement.setDescription(levelRequirementDTO.description());
    return levelRequirement;
  }

  // CreateLevelRequirementResponseDTO
  public CreateLevelRequirementResponseDTO toCreateResponseDTO(LevelRequirement levelRequirement) {
    if (levelRequirement == null) {
      return null;
    } 
    return new CreateLevelRequirementResponseDTO(
        levelRequirement.getLevelRequirementId(),
        levelRequirement.getRequirement(),
        levelRequirement.getDescription(),
        levelRequirement.getLevel().getLevelId());
  }
  
  // LevelRequirementDTO - Response 
  public LevelRequirementDTO toLevelRequirementDTO(LevelRequirement requirement) {
    return new LevelRequirementDTO(
        requirement.getLevelRequirementId(),
        requirement.getRequirement());
  }

  // LevelRequirementSummaryDTO - Response
  public LevelRequirementSummaryDTO toSummaryDTO(LevelRequirement levelRequirement) {
    if (levelRequirement == null) {
      return null;
    }
    return new LevelRequirementSummaryDTO(
        levelRequirement.getLevelRequirementId(),
        levelRequirement.getRequirement(),
        levelRequirement.getDescription(),
        levelRequirement.getLevel().getLevelId(),
        levelRequirement.getLevel().getLevelName()
        );
  }

  // UpdateLevelRequirementEntity
  public void updateLevelRequirementInfoEntity(LevelRequirement levelRequirement, UpdateLevelRequirementInfoDTO dto) {
  levelRequirement.setRequirement(dto.requirement());
  levelRequirement.setDescription(dto.description());
  }
}
