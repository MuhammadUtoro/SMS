package dto.swimmer;

public record UpdateSwimmerInfoDTO(
                String first_name,
                String last_name,
                Long parent_id,
                Long level_id,
                Long course_id
                ) {

}
