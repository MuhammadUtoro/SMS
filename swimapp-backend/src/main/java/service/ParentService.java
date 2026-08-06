package service;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import dto.parent.ParentInfoDTO;
import dto.parent.ParentRegistrationRequestDTO;
import dto.parent.ParentRegistrationResponseDTO;
import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import dto.swimmer.SwimmerSummaryDTO;
import dto.user.UserRegistrationDTO;
import entity.Parent;
import entity.Swimmer;
import enums.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.ParentMapper;
import mapper.SwimmerMapper;
import repository.ParentRepository;
import repository.SwimmerRepository;

@ApplicationScoped
public class ParentService {

    @Inject
    KeycloakAdminService keycloakAdminService;

    @Inject
    ParentMapper parentMapper;

    @Inject
    SwimmerMapper swimmerMapper;

    @Inject
    ParentRepository parentRepository;

    @Inject
    SwimmerRepository swimmerRepository;

    @Inject
    JsonWebToken jwt;

    // Register User - Parent
    @Transactional
    public ParentRegistrationResponseDTO registerParent(ParentRegistrationRequestDTO dto) {
        UUID keycloakUserId = null;

        try {
            
            UserRegistrationDTO userDTO = new UserRegistrationDTO(
                    dto.email(),
                    dto.firstName(),
                    dto.lastName(),
                    dto.username(),
                    dto.password()
                    );

            keycloakUserId = keycloakAdminService.createUser(userDTO, UserRole.PARENT);
            Parent parent = new Parent();

            parent.setKeycloakUserId(keycloakUserId);
            parent.setEmail(dto.email());
            parent.setFirstName(dto.firstName());
            parent.setLastName(dto.lastName());
            parent.setUsername(dto.username());

            parentRepository.persist(parent);

            return new ParentRegistrationResponseDTO(
                    dto.email(),
                    dto.firstName(),
                    dto.lastName());
        } catch (Exception e) {
            if (keycloakUserId != null) {
                keycloakAdminService.deleteUser(keycloakUserId);
            }
            throw e;
        }
    }

    // Retrieve all parents - GET
    public List<ParentSummaryDTO> getParentsList(int page, int size) {
        List<Parent> parents = parentRepository.getParentsList(page, size);
        return parents.stream().map(
                parentMapper::toSummaryDTO).toList();
    }

    // Retrieve Parent by ID
    public ParentSummaryDTO getParentById(Long parentId) {
        Parent parent = parentRepository.findParentById(parentId);

        return parentMapper.toSummaryDTO(parent);
    }

    // Update Parent - PUT
    public ParentSummaryDTO updateParentInfoEntity(Long parentId, UpdateParentInfoDTO dto) {
        Parent parent = parentRepository.findParentById(parentId);
        if (parent == null) {
            throw new NotFoundException("Parent with ID: " + parentId + " is not found!");
        }
        parentMapper.UpdateParentInfoEntity(parent, dto);
        return parentMapper.toSummaryDTO(parent);
    }

    // Get parent profile
    public ParentInfoDTO getMyProfile() {
        UUID keycloakUserId = UUID.fromString(jwt.getSubject());

        if (keycloakUserId == null) {
                throw new NotFoundException("User not found!");
        }

        Parent parent = parentRepository.findByKeycloakUserId(keycloakUserId);

        return parentMapper.toProfileDTO(parent);
    }

    // Get list of swimmers according to parentId
    public List<SwimmerSummaryDTO> getMySwimmers() {

        String subject = jwt.getSubject();
        if (subject == null) {
            throw new NotFoundException("Keycloak id ot found!");
        }
        UUID keycloakUserId = UUID.fromString(subject);

        Parent parent = parentRepository.findByKeycloakUserId(keycloakUserId);

        if (parent == null) {
            throw new NotFoundException("Parent not found!");
        }

        List<Swimmer> swimmers = swimmerRepository.findSwimmersByParent(parent);

        return swimmers.stream().map(
                swimmerMapper::toSummaryDTO
                ).toList();
    }
}
