package service;

import java.util.List;

import dto.trainer.CreateTrainerDTO;
import dto.trainer.CreateTrainerResponseDTO;
import dto.trainer.TrainerSummaryDTO;
import dto.trainer.UpdateTrainerInfoDTO;
import entity.Trainer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import mapper.TrainerMapper;
import repository.CourseRepository;
import repository.TrainerRepository;

@ApplicationScoped
public class TrainerService {

    @Inject
    TrainerMapper trainerMapper;

    @Inject
    TrainerRepository trainerRepository;

    @Inject
    CourseRepository courseRepository;

    // Create Trainer(request) - POST
    @Transactional
    public CreateTrainerResponseDTO createTrainer(CreateTrainerDTO dto) {
        Trainer trainer = trainerMapper.toCreateEntity(dto);
        // Persist
        trainerRepository.persist(trainer);
        return trainerMapper.toCreateResponseDTO(trainer);
    }

    // Retrieve all trainers - GET
    public List<TrainerSummaryDTO> getTrainersList(int page, int size) {
        List<Trainer> trainers = trainerRepository.getTrainersList(page, size);
        return trainers.stream().map(
                trainerMapper::toSummaryDTO).toList();
    }

    // Retrieve trainer by ID - GET
    public TrainerSummaryDTO getTrainerById(Long trainerId) {
        Trainer trainer = trainerRepository.findTrainerById(trainerId);
        if (trainer == null) {
            throw new NotFoundException("Trainer with ID: " + trainerId + " is not found!");
        }
        return trainerMapper.toSummaryDTO(trainer);
    }

    // Update trainer info - PUT
    @Transactional
    public TrainerSummaryDTO updateTrainerInfoEntity(Long trainerId, UpdateTrainerInfoDTO dto) {
        Trainer trainer = trainerRepository.findTrainerById(trainerId);
        if (trainer == null) {
            throw new NotFoundException("Trainer with ID: " + trainerId + " is not found!");
        }

        trainerMapper.updateTrainerInfoEntity(trainer, dto);
        return trainerMapper.toSummaryDTO(trainer);
    }

    @Transactional
    public void deleteTrainer(Long trainerId) {
        boolean deleted = trainerRepository.deleteTrainer(trainerId);

        if (!deleted) {
            throw new NotFoundException("Trainer not found!");
        }
    }
}
