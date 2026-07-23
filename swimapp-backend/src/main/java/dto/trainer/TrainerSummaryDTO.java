package dto.trainer;

import java.util.List;

import dto.course.CourseDetailDTO;

public record TrainerSummaryDTO(
        String first_name,
        String last_name,
        List<CourseDetailDTO> courses
        ) {

}
