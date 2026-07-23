package dto.course;

import java.time.LocalTime;

public record CourseDetailDTO(
        String course_name,
        String course_day,
        LocalTime course_time,
        String level_name
        ) {

}
