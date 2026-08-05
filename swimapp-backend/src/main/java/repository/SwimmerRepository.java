package repository;

import java.util.List;

import entity.Parent;
import entity.Swimmer;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

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
  public Swimmer findSwimmerById(Long swimmerId) {
    return findById(swimmerId);
  }
  
  // Find swimmer's parent by Id
  public List<Swimmer> findSwimmersByParent(Parent parent) {
   return find("parent", parent).list();
  }

  // Delete swimmer
  public boolean deleteSwimmer(Long swimmer_id) {
    return deleteById(swimmer_id);
  }
}
