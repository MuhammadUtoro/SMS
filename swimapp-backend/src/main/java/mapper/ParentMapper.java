package mapper;

import dto.parent.ParentSummaryDTO;
import dto.parent.UpdateParentInfoDTO;
import entity.Parent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParentMapper {

  // Entity to DTO - ResponseDTO
  public ParentSummaryDTO toSummaryDTO(Parent parent) {
    if (parent == null) {
      return null;
    }
    return new ParentSummaryDTO(
        parent.getEmail(),
        parent.getSwimmers()
        );
  }

  // DTO to Entity - RequestDTO
    public void UpdateParentInfoEntity(Parent parent, UpdateParentInfoDTO dto) {
      parent.setEmail(dto.email());
    }
}
