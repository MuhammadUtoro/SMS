package dto.level;

import java.util.List;
import dto.levelrequirement.LevelRequirementDTO;

public record LevelSummaryDTO(
                Long levelId,
                String levelName,
                List<LevelRequirementDTO> requirements
                ) {

}
