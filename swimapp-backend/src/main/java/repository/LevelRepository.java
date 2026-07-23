package repository;

import java.util.List;

import entity.Level;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class LevelRepository implements PanacheRepository<Level> {
  
  // Method to retrieve all level
  public List<Level> getAllLevels() {
    PanacheQuery<Level> levels = findAll();
    return levels.list();
  }

  // Method to retrieve all levels with pagination
  public List<Level> getLevelsList(int page, int size) {
    PanacheQuery<Level> levelQuery = findAll();
    levelQuery.page(Page.of(page, size));
    return levelQuery.list();
  }

  // Method to find level by level_name
  public Level findLevelByName(String level_name) {
    return find("level_name", level_name).firstResult();
  }

  // Find by ID
  public Level findLevelById(Long level_id) {
    return findById(level_id);
  }

  // Delete By Id
  public boolean deleteLevel(Long level_id) {
    return deleteById(level_id);
  }
}
