package mapper;

import dto.swimmer.CreateSwimmerDTO;
import dto.swimmer.CreateSwimmerResponseDTO;
import dto.swimmer.SwimmerDetailDTO;
import dto.swimmer.SwimmerSummaryDTO;
import dto.swimmer.UpdateSwimmerInfoDTO;
import entity.Swimmer;
import jakarta.enterprise.context.ApplicationScoped;

// Mappers should handle only fields' values not relationship!
// Keep them clean

@ApplicationScoped
public class SwimmerMapper {
  
  // CreateSwimmerDTO
  // RequestDTO
  public Swimmer toCreateEntity(CreateSwimmerDTO swimmerDTO) {
    Swimmer swimmer = new Swimmer();
    swimmer.setFirstName(swimmerDTO.firstName());
    swimmer.setLastName(swimmerDTO.lastName());
    swimmer.setDob(swimmerDTO.dateOfBirth());
    return swimmer;
  }

  // CreateSwimmerResponseDTO
  // ResponseDTO
  public CreateSwimmerResponseDTO toCreateResponseDTO(Swimmer swimmer) {
    if (swimmer == null) {
      return null;
    }
    return new CreateSwimmerResponseDTO(
        swimmer.getSwimmerId(),
        swimmer.getFirstName(),
        swimmer.getLastName()
        );
  }

  // Map from entity to DTO - for summaryDTO
  // ResponseDTO
  public SwimmerSummaryDTO toSummaryDTO(Swimmer swimmer) {
    if (swimmer == null) {
      return null;
    }
    return new SwimmerSummaryDTO(
        swimmer.getSwimmerId(),
        swimmer.getFirstName(),
        swimmer.getLastName(),
        swimmer.getLevel().getLevelName(),
        swimmer.getCourse().getCourseName(),
        swimmer.getCourse().getCourseDay(),
        swimmer.getCourse().getCourseTime());
  }

  // Map from entity to DTO - for detailDTO (with IDs only)
  // ResponseDTO
  public SwimmerDetailDTO toDetailDTO(Swimmer swimmer) {
    if (swimmer == null) {
      return null;
    }
    return new SwimmerDetailDTO(
        swimmer.getSwimmerId(),
        swimmer.getFirstName(),
        swimmer.getLastName(),
        swimmer.getParent().getParentId(),
        swimmer.getParent().getEmail(),
        swimmer.getLevel().getLevelId(),
        swimmer.getCourse().getCourseId());
  }


  // Map from DTO to entity - just in case admin needs to update swimmer's
  // detail
  // RequestDTO
  public void updateSwimmerInfoEntity(Swimmer swimmer, UpdateSwimmerInfoDTO dto) {
    swimmer.setFirstName(dto.firstName());
    swimmer.setLastName(dto.lastName());
  }

}
