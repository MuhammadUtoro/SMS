package repository;

import java.util.List;
import entity.Swimmer;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@ApplicationScoped
public class SwimmerRepository implements PanacheRepository<Swimmer> {
  // Retrieve all swimmers with pagination
  public List<Swimmer> getAllSwimmers() {
    return listAll();
  }

  // Retrieve all swimmers with pagination
  public List<Swimmer> getSwimmersList(int page, int size) {
    PanacheQuery<Swimmer> swimmerQuery = findAll();
    swimmerQuery.page(Page.of(page, size));
    return swimmerQuery.list();
  }

  // Find swimmer by Id
  public Swimmer findSwimmerById(Long swimmer_id) {
    return findById(swimmer_id);
  }
  
  // Find swimmer's parent by Id
  public List<Swimmer> findSwimmersByParentId(Long parent_id) {
   return find("parent.parent_id", parent_id).list();
  }

  // Delete swimmer
  public boolean deleteSwimmer(Long swimmer_id) {
    return deleteById(swimmer_id);
  }
}
