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
  private Long trainerId;

  @Column(name="keycloak_user_id")
  private UUID keycloakUserId;
  
  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  // Set the One to Many relationship with course
  @OneToMany(mappedBy="trainer")
  private List<Course> courses = new ArrayList<>();

  // Getter and Setter
  public Long getTrainerId() {
    return trainerId;
  }

  public void setTrainerId(Long trainerId) {
    this.trainerId = trainerId;
  }
  
  public UUID getKeyCloakUserId() {
    return keycloakUserId;
  }

  public void setKeyCloakUserId(UUID keycloakUserId) {
    this.keycloakUserId = keycloakUserId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String last_name) {
    this.lastName = lastName;
  } 

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
}
