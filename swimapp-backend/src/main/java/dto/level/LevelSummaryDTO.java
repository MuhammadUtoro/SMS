package dto.level;

import java.util.List;
import dto.levelrequirement.LevelRequirementDTO;

public record LevelSummaryDTO(
                Long level_id,
                String level_name,
                List<LevelRequirementDTO> requirements
                ) {

}
