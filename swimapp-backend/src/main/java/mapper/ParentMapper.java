package mapper;

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

  // DTO to Entity - RequestDTO
    public void UpdateParentInfoEntity(Parent parent, UpdateParentInfoDTO dto) {
      parent.setEmail(dto.email());
    }
}
