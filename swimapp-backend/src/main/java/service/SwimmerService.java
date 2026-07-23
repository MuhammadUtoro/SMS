package service;

import java.util.List;

import dto.swimmer.CreateSwimmerDTO;
import dto.swimmer.CreateSwimmerResponseDTO;
import dto.swimmer.SwimmerDetailDTO;
import dto.swimmer.SwimmerSummaryDTO;
import dto.swimmer.UpdateSwimmerCourseDTO;
import dto.swimmer.UpdateSwimmerInfoDTO;
import dto.swimmer.UpdateSwimmerLevelDTO;
import entity.Course;
import entity.Level;
import entity.Parent;
import entity.Swimmer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.transaction.Transactional;
import mapper.SwimmerMapper;
import repository.CourseRepository;
import repository.LevelRepository;
import repository.ParentRepository;
import repository.SwimmerRepository;

@ApplicationScoped
public class SwimmerService {

    @Inject
    SwimmerRepository swimmerRepository;

    @Inject
    ParentRepository parentRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    LevelRepository levelRepository;

    @Inject
    SwimmerMapper swimmerMapper;

    // Create Swimmer(request) - POST
    @Transactional
    public CreateSwimmerResponseDTO createSwimmer(CreateSwimmerDTO dto) {
        Swimmer swimmer = swimmerMapper.toCreateEntity(dto);
        // Persist
        swimmerRepository.persist(swimmer);
        return swimmerMapper.toCreateResponseDTO(swimmer);
    }

    // Retrieve all swimmers
    public List<SwimmerSummaryDTO> getSwimmersList(int page, int size) {
        List<Swimmer> swimmers = swimmerRepository.getSwimmersList(page, size);
        return swimmers.stream().map(
                swimmerMapper::toSummaryDTO).toList();
    }

    // Retrieve swimmer by ID - GET
    public SwimmerDetailDTO getSwimmerById(Long swimmer_id) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmer_id);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer with ID: " + swimmer_id + " is not found!");
        }
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Retrieve all swimmers' details - GET
    public List<SwimmerDetailDTO> getSwimmersDetailList(int page, int size) {
        List<Swimmer> swimmers = swimmerRepository.getSwimmersList(page, size);
        return swimmers.stream().map(
                swimmerMapper::toDetailDTO).toList();
    }

    // Update Swimmer Info - for admin - PUT
    @Transactional
    public SwimmerDetailDTO updateSwimmerInfoEntity(Long swimmer_id, UpdateSwimmerInfoDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmer_id);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer with ID: " + swimmer_id + " is not found!");
        }
        // Use the mapper for convert the values
        swimmerMapper.updateSwimmerInfoEntity(swimmer, dto);

        // Parent
        Parent parent = parentRepository.findParentById(dto.parent_id());
        if (parent == null) {
            throw new NotFoundException("Parent not found!");
        }
        swimmer.setParent(parent);

        // Level
        Level level = levelRepository.findLevelById(dto.level_id());
        if (level == null) {
            throw new NotFoundException("Level not found!");
        }
        swimmer.setLevel(level);

        // Course
        Course course = courseRepository.findCourseById(dto.course_id());
        if (course == null) {
            throw new NotFoundException("Course not found!");
        }
        swimmer.setCourse(course);

        // Return SwimmerDetailDTO
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Update Level - PATCH
    @Transactional
    public SwimmerDetailDTO updateSwimmerLevel(Long swimmer_id, UpdateSwimmerLevelDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmer_id);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer not found!");
        }

        // Fetch level
        Level level = levelRepository.findLevelById(dto.level_id());
        if (level == null) {
            throw new NotFoundException("Level not found!");
        }
        swimmer.setLevel(level);
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Update Course - PATCH
    @Transactional
    public SwimmerDetailDTO updateSwimmerCourse(Long swimmer_id, UpdateSwimmerCourseDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmer_id);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer not found!");
        }

        // Fetch course
        Course course = courseRepository.findCourseById(dto.course_id());
        if (course == null) {
            throw new NotFoundException("Course not found!");
        }
        swimmer.setCourse(course);
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Delete swimmer - DELETE
    @Transactional
    public void deleteSwimmer(Long swimmer_id) {

        boolean deleted = swimmerRepository.deleteSwimmer(swimmer_id);

        if (!deleted) {
            throw new NotFoundException("Swimmer not found!");
        }
    }
}
