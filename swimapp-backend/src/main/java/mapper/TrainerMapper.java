package mapper;

import dto.trainer.CreateTrainerDTO;
import dto.trainer.CreateTrainerResponseDTO;
import dto.trainer.TrainerSummaryDTO;
import dto.trainer.UpdateTrainerInfoDTO;
import entity.Trainer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TrainerMapper {

  @Inject
  CourseMapper courseMapper;

  // CreateTrainerDTO - Request
  public Trainer toCreateEntity(CreateTrainerDTO trainerDTO) {
    Trainer trainer = new Trainer();
    trainer.setFirstName(trainerDTO.firstName());
    trainer.setLastName(trainerDTO.lastName());
    return trainer;
  }

  // CreateTrainerResponseDTO - Response
  public CreateTrainerResponseDTO toCreateResponseDTO(Trainer trainer) {
    if (trainer == null) {
      return null;
    }
    return new CreateTrainerResponseDTO(
        trainer.getTrainerId(),
        trainer.getKeyCloakUserId(),
        trainer.getFirstName(),
        trainer.getLastName());
  }

  // TrainerSummaryDTO - Response
  public TrainerSummaryDTO toSummaryDTO(Trainer trainer) {
    if (trainer == null) {
      return null;
    }
    return new TrainerSummaryDTO(
        trainer.getFirstName(),
        trainer.getLastName(),
        trainer.getCourses().stream()
        .map(
          courseMapper::toDetailDTO
          ).toList()
        );
  }

  // Update Trainer Info
  public void updateTrainerInfoEntity(Trainer trainer, UpdateTrainerInfoDTO dto) {
    trainer.setFirstName(dto.firstName());
    trainer.setLastName(dto.lastName());
  }
}
