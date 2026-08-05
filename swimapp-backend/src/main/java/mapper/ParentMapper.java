package mapper;

import dto.parent.ParentInfoDTO;
import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import entity.Parent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ParentMapper {

  @Inject
  SwimmerMapper swimmerMapper;

  // Entity to DTO - ResponseDTO
  public ParentSummaryDTO toSummaryDTO(Parent parent) {
    if (parent == null) {
      return null;
    }
    return new ParentSummaryDTO(
        parent.getEmail(),
        parent.getSwimmers().stream().map(
          swimmerMapper::toSummaryDTO
          ).toList());
  }

  // To profile - Entity to DTO
  public ParentInfoDTO toProfileDTO(Parent parent) {
    if (parent == null) {
      return null;
    }
    return new ParentInfoDTO(
        parent.getParentId(),
        parent.getFirstName(),
        parent.getLastName(),
        parent.getUsername(),
        parent.getEmail()
        );
  }

  // DTO to Entity - RequestDTO
    public void UpdateParentInfoEntity(Parent parent, UpdateParentInfoDTO dto) {
      parent.setEmail(dto.email());
    }
}
