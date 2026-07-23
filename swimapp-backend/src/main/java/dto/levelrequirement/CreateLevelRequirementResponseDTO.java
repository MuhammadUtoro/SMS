package dto.levelrequirement;

public record CreateLevelRequirementResponseDTO(
        Long level_requirement_id,
        String requirement,
        String description,
        Long level_id
        ) {

}
