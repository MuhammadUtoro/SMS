package entity;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="trainers")
public class Trainer {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="trainer_id")
  private Long trainer_id;

  @Column(name="keycloak_user_id")
  private UUID keycloak_user_id;
  
  @Column(name="first_name")
  private String first_name;

  @Column(name="last_name")
  private String last_name;

  // Set the One to Many relationship with course
  @OneToMany(mappedBy="trainer")
  private List<Course> courses = new ArrayList<>();

  // Getter and Setter
  public Long getTrainerId() {
    return trainer_id;
  }

  public void setTrainerId(Long trainer_id) {
    this.trainer_id = trainer_id;
  }
  
  public UUID getKeyCloakUserId() {
    return keycloak_user_id;
  }

  public void setKeyCloakUserId(UUID keycloak_user_id) {
    this.keycloak_user_id = keycloak_user_id;
  }

  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public void setLastName(String last_name) {
    this.last_name =last_name;
  } 

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
