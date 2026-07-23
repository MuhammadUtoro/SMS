package repository;

import java.util.List;

import entity.Parent;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParentRepository implements PanacheRepository<Parent> {

  // We can use this repository to retrieve List of parents and
  // find the parents by Id

  // Method to retrieve all parents without pagination
  public List<Parent> getAllParents() {
    PanacheQuery<Parent> parents = findAll();
    return parents.list();
  }

  // Method to retrieve parents with pagination
  public List<Parent> getParentsList(int page, int size) {
    // Using PanacheQuery
    PanacheQuery<Parent> parentQuery = findAll();
    parentQuery.page(Page.of(page, size));
    return parentQuery.list();
  }
  
  // Find by Id
  public Parent findParentById(Long parent_id) {
    return findById(parent_id);
  }

  // Find by Email
  public Parent findParentByEmail(String email) {
    return find("email", email).firstResult();
  }
}
