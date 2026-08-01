package dto.course;

import java.time.LocalTime;

public record CreateCourseDTO(
                Long levelId,
                Long trainerId,
                String courseName,
                String courseDay,
                LocalTime courseTime) {

}
