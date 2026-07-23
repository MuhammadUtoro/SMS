package dto.course;

import java.time.LocalTime;

public record CourseSummaryDTO(
    Long course_id,
    String course_name,
    String course_day,
    LocalTime course_time,
    String trainer_first_name,
    String trainer_last_name,
    String level_name
    ) {

}
