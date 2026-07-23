package mapper;

import dto.course.CourseDetailDTO;
import dto.course.CourseSummaryDTO;
import dto.course.CreateCourseDTO;
import dto.course.CreateCourseResponseDTO;
import dto.course.UpdateCourseInfoDTO;
import entity.Course;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseMapper {

  // CreateTrainerDTO - Request
  public Course toCreateEntity(CreateCourseDTO courseDTO) {
    Course course = new Course();
    course.setCourseName(courseDTO.course_name());
    course.setCourseDay(courseDTO.course_day());
    course.setCourseTime(courseDTO.course_time());
    return course;
  }

  // CreateCourseResponseDTO - Response
  public CreateCourseResponseDTO toCreateResponseDTO(Course course) {
    if (course == null) {
      return null;
    }
    return new CreateCourseResponseDTO(
        course.getCourseId(),
        course.getCourseName(),
        course.getCourseDay(),
        course.getCourseTime());
  }

  // CourseSummaryDTO - Response
  public CourseSummaryDTO toSummaryDTO(Course course) {
    if (course == null) {
      return null;
    }
    return new CourseSummaryDTO(
        course.getCourseId(),
        course.getCourseName(),
        course.getCourseDay(),
        course.getCourseTime(),
        course.getTrainer().getFirstName(),
        course.getTrainer().getLastName(),
        course.getLevel().getLevelName());
  }

  // CourseDetailDTO
  public CourseDetailDTO toDetailDTO(Course course) {
    if (course == null) {
      return  null;
    }
    return new CourseDetailDTO(
        course.getCourseName(),
        course.getCourseDay(),
        course.getCourseTime(),
        course.getLevel().getLevelName()
        );
  }

  // CoursUpdateInfo - Request
  public void updateCourseInfoEntity(Course course, UpdateCourseInfoDTO dto) {
    course.setCourseName(dto.course_name());
    course.setCourseDay(dto.course_day());
    course.setCourseTime(dto.course_time());
  }
}
