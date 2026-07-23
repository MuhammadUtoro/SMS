package mapper;

import dto.level.CreateLevelDTO;
import dto.level.CreateLevelResponseDTO;
import dto.level.LevelSummaryDTO;
import dto.level.UpdateLevelInfoDTO;
import entity.Level;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LevelMapper {

  @Inject
  LevelRequirementMapper levelRequirementMapper;

  // CreateLevelDTO - Request
  public Level toCreateEntity(CreateLevelDTO levelDTO) {
    Level level = new Level();
    level.setLevelName(levelDTO.level_name());
    return level;
  }

  // CreateLevelResponseDTO - Response
  public CreateLevelResponseDTO toCreateResponseDTO(Level level) {
    if (level == null) {
      return null;
    }
    return new CreateLevelResponseDTO(
        level.getLevelId(),
        level.getLevelName()
        );
  }

  // LevelSummaryDTO - Response
  public LevelSummaryDTO toSummaryDTO(Level level) {
    if (level == null) {
      return null;
    }
    return new LevelSummaryDTO(
        level.getLevelId(),
        level.getLevelName(),
        level.getRequirements()
              .stream()
              .map(levelRequirementMapper::toLevelRequirementDTO)
              .toList()
        );
  }

  // UpdateLevelInfoDTO - Request
  public void updateLevelInfoEntity(Level level, UpdateLevelInfoDTO dto) {
    level.setLevelName(dto.level_name());
  }
}

