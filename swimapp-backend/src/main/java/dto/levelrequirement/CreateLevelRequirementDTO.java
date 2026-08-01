package dto.levelrequirement;

public record CreateLevelRequirementDTO(
    Long levelId,
    String requirement,
    String description
    ) {

}
