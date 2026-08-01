package dto.trainer;

import java.util.List;

import dto.course.CourseDetailDTO;

public record TrainerSummaryDTO(
        String firstName,
        String lastName,
        List<CourseDetailDTO> courses
        ) {

}
