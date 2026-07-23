package dto.levelrequirement;

public record CreateLevelRequirementDTO(
    Long level_id,
    String requirement,
    String description
    ) {

}
