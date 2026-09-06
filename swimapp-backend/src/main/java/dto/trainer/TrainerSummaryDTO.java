package dto.trainer;

import java.util.List;

import dto.course.CourseDetailDTO;

public record TrainerSummaryDTO(
        Long trainerId,
        String firstName,
        String lastName,
        List<CourseDetailDTO> courses
        ) {

}
