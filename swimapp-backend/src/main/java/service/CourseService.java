package service;

import java.util.List;

import dto.course.CourseSummaryDTO;
import dto.course.CreateCourseDTO;
import dto.course.CreateCourseResponseDTO;
import dto.course.UpdateCourseInfoDTO;
import dto.course.UpdateCourseLevelDTO;
import dto.course.UpdateCourseTrainerDTO;
import entity.Course;
import entity.Level;
import entity.Trainer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.CourseMapper;
import repository.CourseRepository;
import repository.LevelRepository;
import repository.TrainerRepository;

@ApplicationScoped
public class CourseService {

    @Inject
    CourseMapper courseMapper;

    @Inject
    CourseRepository courseRepository;

    @Inject
    LevelRepository levelRepository;

    @Inject
    TrainerRepository trainerRepository;

    // Create Course(request) - POST
    @Transactional
    public CreateCourseResponseDTO createCourse(CreateCourseDTO dto) {
        Course course = courseMapper.toCreateEntity(dto);
        // Add level_id
        Level level = levelRepository.findLevelById(dto.level_id());
        // Set level to course
        course.setLevel(level);
        // Add trainer_id
        Trainer trainer = trainerRepository.findTrainerById(dto.trainer_id());
        // set trainer to course
        course.setTrainer(trainer);
        // Persist
        courseRepository.persist(course);
        return courseMapper.toCreateResponseDTO(course);
    }

    // Retrieve all courses - GET
    public List<CourseSummaryDTO> getCoursesList(int page, int size) {
        List<Course> courses = courseRepository.getCourseList(page, size);
        return courses.stream().map(
                courseMapper::toSummaryDTO).toList();
    }

    // Retrieve course by ID - GET
    public CourseSummaryDTO getCourseById(Long course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new NotFoundException("Course with ID: " + course_id + " is not found!");
        }
        return courseMapper.toSummaryDTO(course);
    }

    // UpdateCourseInfoDTO - PUT
    @Transactional
    public CourseSummaryDTO updateCourseInfoEntity(Long course_id, UpdateCourseInfoDTO dto) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new NotFoundException("Course with ID: " + course_id + " is not found!");
        }
        courseMapper.updateCourseInfoEntity(course, dto);
        return courseMapper.toSummaryDTO(course);
    }

    // UpdateCourseLevelDTO - PATCH
    @Transactional
    public CourseSummaryDTO updateCourseLevel(Long course_id, UpdateCourseLevelDTO dto) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new NotFoundException("Course not found!");
        }

        // Fetch level
        Level level = levelRepository.findLevelById(dto.level_id());
        if (level == null) {
            throw new NotFoundException("Level not found!");
        }
        course.setLevel(level);
        return courseMapper.toSummaryDTO(course);
    }

    // UpdateCourseTrainerDTO - PATCH
    @Transactional
    public CourseSummaryDTO updateCourseTrainer(Long course_id, UpdateCourseTrainerDTO dto) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw new NotFoundException("Course not found!");
        }

        // Fetch trainer
        Trainer trainer = trainerRepository.findTrainerById(dto.trainer_id());
        if (trainer == null) {
            throw new NotFoundException("Trainer not found!");
        }

        course.setTrainer(trainer);
        return courseMapper.toSummaryDTO(course);
    }

    // Delete Course
    @Transactional
    public void deleteCourse(Long course_id) {
        boolean deleted = courseRepository.deleteCourse(course_id);
        if (!deleted) {
            throw new NotFoundException("Course not found!");
        }
    }
}
