package dto.levelrequirement;

public record LevelRequirementSummaryDTO(
        Long level_requirement_id,
        String requirement,
        String description,
        Long level_id,
        String levelName
        ) {

}
