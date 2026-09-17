package dto.swimmer;

// This DTO is to show swimmer detail info (only ID)
// For response(GET)
public record SwimmerDetailDTO(
    Long swimmerId,
    String firstName,
    String lastName,
    Long parentId,
    String parentEmail,
    Long levelId,
    String levelName,
    Long courseId,
    String courseName
    ) {

}
