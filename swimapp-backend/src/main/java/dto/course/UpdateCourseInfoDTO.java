package dto.course;

import java.time.LocalTime;

public record UpdateCourseInfoDTO(
        String course_name,
        String course_day,
        LocalTime course_time
        ) {

}
