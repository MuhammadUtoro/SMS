package mapper;

import dto.course.CreateCourseDTO;
import entity.Course;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseMapperTest {

    @Test
    void toCreateEntity_shouldCopyCourseFields() {
        // Arrange: prepare the input
        CreateCourseDTO dto = new CreateCourseDTO(
            1L,
            2L,
            "Beginner Swimming",
            "Monday",
            LocalTime.of(10, 30)
        );

        CourseMapper mapper = new CourseMapper();

        // Act: execute the method
        Course course = mapper.toCreateEntity(dto);

        // Assert: verify the result
        assertEquals("Beginner Swimming", course.getCourseName());
        assertEquals("Monday", course.getCourseDay());
        assertEquals(LocalTime.of(10, 30), course.getCourseTime());
    }
}
