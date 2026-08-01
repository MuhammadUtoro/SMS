package dto.levelrequirement;

public record CreateLevelRequirementResponseDTO(
        Long levelRequirementId,
        String requirement,
        String description,
        Long levelId
        ) {

}
