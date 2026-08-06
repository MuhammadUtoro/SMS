package repository;

import java.util.List;
import java.util.UUID;

import entity.Trainer;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TrainerRepository implements PanacheRepository<Trainer> {
  
  // Method to retrieve all trainers without pagination
  public List<Trainer> getAllTrainers() {
    PanacheQuery<Trainer> trainers = findAll();
    return trainers.list();
  }

  // With pagination
  public List<Trainer> getTrainersList(int page, int size) {
    // Using PanacheQuery
    PanacheQuery<Trainer> trainerQuery = findAll();
    trainerQuery.page(Page.of(page, size));
    return trainerQuery.list();
  }

  // Find trainer from first_name
  public Trainer findTrainerByFirstName(String first_name) {
    return find("first_name", first_name).firstResult();
  } 

  // Find trainer by Id
  public Trainer findTrainerById(Long trainer_id) {
    return findById(trainer_id);
  }

  public Trainer findByKeycloakUserId(UUID id) {
    return find("keycloakUserId", id).firstResult();
  }

  // Delete trainer
  public boolean deleteTrainer(Long trainer_id) {
    return deleteById(trainer_id);
  }
}
