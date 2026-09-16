package service;

import java.util.List;
import java.util.UUID;

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
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.security.identity.SecurityIdentity;
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

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    // Create Swimmer(request) - POST
    @Transactional
    public CreateSwimmerResponseDTO createSwimmer(CreateSwimmerDTO dto) {
        // Retrieve Id
        UUID keycloakUserId  = UUID.fromString(jwt.getSubject()); 
        // Retrieve Parent
        Parent parent = parentRepository.findByKeycloakUserId(keycloakUserId);

        Swimmer swimmer = swimmerMapper.toCreateEntity(dto);
        swimmer.setParent(parent);
        
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
    public SwimmerDetailDTO getSwimmerById(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmerId);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer with ID: " + swimmerId + " is not found!");
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
    public SwimmerDetailDTO updateSwimmerInfoEntity(Long swimmerId, UpdateSwimmerInfoDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmerId);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer with ID: " + swimmerId + " is not found!");
        }
        // Use the mapper for convert the values
        swimmerMapper.updateSwimmerInfoEntity(swimmer, dto);

        // Parent
        Parent parent = parentRepository.findParentById(dto.parentId());
        if (parent == null) {
            throw new NotFoundException("Parent not found!");
        }
        swimmer.setParent(parent);

        // Return SwimmerDetailDTO
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Update Level - PATCH
    @Transactional
    public SwimmerDetailDTO updateSwimmerLevel(Long swimmerId, UpdateSwimmerLevelDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmerId);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer not found!");
        }

        // Fetch level
        Level level = levelRepository.findLevelById(dto.levelId());
        if (level == null) {
            throw new NotFoundException("Level not found!");
        }
        swimmer.setLevel(level);
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Update Course - PATCH
    @Transactional
    public SwimmerDetailDTO updateSwimmerCourse(Long swimmerId, UpdateSwimmerCourseDTO dto) {
        Swimmer swimmer = swimmerRepository.findSwimmerById(swimmerId);
        if (swimmer == null) {
            throw new NotFoundException("Swimmer not found!");
        }

        // Fetch course
        Course course = courseRepository.findCourseById(dto.courseId());
        if (course == null) {
            throw new NotFoundException("Course not found!");
        }
        swimmer.setCourse(course);
        return swimmerMapper.toDetailDTO(swimmer);
    }

    // Delete swimmer - DELETE
    @Transactional
    public void deleteSwimmer(Long swimmerId) {

        boolean deleted = swimmerRepository.deleteSwimmer(swimmerId);

        if (!deleted) {
            throw new NotFoundException("Swimmer not found!");
        }
    }
}
