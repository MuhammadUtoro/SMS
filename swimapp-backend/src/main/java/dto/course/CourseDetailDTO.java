package dto.course;

import java.time.LocalTime;

public record CourseDetailDTO(
        String courseName,
        String courseDay,
        LocalTime courseTime,
        String levelName
        ) {

}
