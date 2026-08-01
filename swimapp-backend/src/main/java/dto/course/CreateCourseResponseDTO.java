package dto.course;

import java.time.LocalTime;

public record CreateCourseResponseDTO(
        Long courseId,
        String courseName,
        String courseDay,
        LocalTime courseTime
        ) {

}
