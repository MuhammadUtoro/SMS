package repository;

import java.util.List;
import entity.LevelRequirement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LevelRequirementRepository implements PanacheRepository<LevelRequirement> {
  
  // Retrieve All Level Requirements
  public List<LevelRequirement> getAllLevelRequirements() {
    return listAll();
  }

  // Method to retrieve all level reqs with pagination
  public List<LevelRequirement> getLevelRequirementsList(int page, int size) {
    PanacheQuery<LevelRequirement> levelRequirementQuery = findAll();
    levelRequirementQuery.page(Page.of(page, size));
    return levelRequirementQuery.list();
  }

  // Find Level Requirement with ID
  public LevelRequirement findLevelRequirementById(Long level_requirement_id) {
    return findById(level_requirement_id);
  }

  public boolean deleteLevelRequirement(Long level_requirement_id) {
    return deleteById(level_requirement_id);
  }
}
