package dto.course;

import java.time.LocalTime;

public record CourseDetailDTO(
        Long courseId,
        String courseName,
        String courseDay,
        LocalTime courseTime,
        String levelName
        ) {

}
