package dto.swimmer;

public record UpdateSwimmerInfoDTO(
                String firstName,
                String lastName,
                Long parentId,
                Long levelId,
                Long courseId
                ) {

}
