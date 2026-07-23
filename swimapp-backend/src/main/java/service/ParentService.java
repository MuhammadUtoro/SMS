package service;

import java.util.List;

import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import entity.Parent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import mapper.ParentMapper;
import repository.ParentRepository;

@ApplicationScoped
public class ParentService {
    
    @Inject
    ParentMapper parentMapper;

    @Inject
    ParentRepository parentRepository;

    // Retrieve all parents - GET
    public List<ParentSummaryDTO> getParentsList(int page, int size) {
        List<Parent> parents = parentRepository.getParentsList(page, size);
        return parents.stream().map(
                parentMapper::toSummaryDTO
                ).toList();
    }

    // Retrieve Parent by ID
    public ParentSummaryDTO getParentById(Long parent_id) {
        Parent parent = parentRepository.findParentById(parent_id);

        return parentMapper.toSummaryDTO(parent);
    }

    // Update Parent - PUT
    public ParentSummaryDTO updateParentInfoEntity(Long parent_id, UpdateParentInfoDTO dto) {
        Parent parent = parentRepository.findParentById(parent_id);
        if (parent == null) {
            throw new NotFoundException("Parent with ID: " + parent_id + " is not found!");
        }
        parentMapper.UpdateParentInfoEntity(parent, dto);
        return parentMapper.toSummaryDTO(parent);
    }
}
