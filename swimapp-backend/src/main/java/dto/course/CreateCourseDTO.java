package dto.course;

import java.time.LocalTime;

public record CreateCourseDTO(
                Long level_id,
                Long trainer_id,
                String course_name,
                String course_day,
                LocalTime course_time) {

}
