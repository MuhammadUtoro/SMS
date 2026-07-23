package dto.course;

import java.time.LocalTime;

public record CreateCourseResponseDTO(
        Long course_id,
        String course_name,
        String course_day,
        LocalTime course_time
        ) {

}
