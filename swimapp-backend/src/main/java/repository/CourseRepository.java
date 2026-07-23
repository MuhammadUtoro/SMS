package repository;

import java.util.List;

import entity.Course;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {
  // Retrieve all courses
  public List<Course> getAllCourses() {
    PanacheQuery<Course> courses = findAll();
    return courses.list();
  }

  // Retrieve Courses with pagination
  public List<Course> getCourseList(int page, int size) {
    PanacheQuery<Course> courseQuery = findAll();
    courseQuery.page(Page.of(page, size));
    return courseQuery.list();
  }

  // Search course by name
  public Course findCourseByName(String course_name) {
    return find("course_name", course_name).firstResult();
  }
  
  // Find by Id
  public Course findCourseById(Long course_id) {
    return findById(course_id);
  }

  // Delete by Id
  public boolean deleteCourse(Long course_id) {
    return deleteById(course_id);
  }
}
