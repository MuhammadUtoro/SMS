package service;

import java.util.List;
import java.util.UUID;

import dto.parent.ParentRegistrationRequestDTO;
import dto.parent.ParentRegistrationResponseDTO;
import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import entity.Parent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import mapper.ParentMapper;
import repository.ParentRepository;

@ApplicationScoped
public class ParentService {

    @Inject
    KeycloakAdminService keycloakAdminService;

    @Inject
    ParentMapper parentMapper;

    @Inject
    ParentRepository parentRepository;

    // Register User - Parent
    @Transactional
    public ParentRegistrationResponseDTO registerParent(ParentRegistrationRequestDTO dto) {
        UUID keycloakUserId = null;

        try {
            keycloakUserId = keycloakAdminService.createUser(dto);
            Parent parent = new Parent();

            parent.setKeycloakUserId(keycloakUserId);
            parent.setEmail(dto.email());

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
}
