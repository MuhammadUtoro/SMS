package dto.course;

import java.time.LocalTime;

public record CourseSummaryDTO(
    Long courseId,
    String courseName,
    String courseDay,
    LocalTime courseTime,
    String trainerFirstName,
    String trainerLastName,
    String levelName
    ) {

}
