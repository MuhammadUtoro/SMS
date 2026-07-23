package dto.swimmer;

// This DTO is to show swimmer detail info (only ID)
// For response(GET)
public record SwimmerDetailDTO(
    Long swimmer_id,
    String first_name,
    String last_name,
    Long parent_id,
    String parent_email,
    Long level_id,
    Long course_id
    ) {

}
