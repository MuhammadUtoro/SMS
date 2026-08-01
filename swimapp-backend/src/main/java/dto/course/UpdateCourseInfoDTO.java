package dto.course;

import java.time.LocalTime;

public record UpdateCourseInfoDTO(
        String courseName,
        String courseDay,
        LocalTime courseTime
        ) {

}
