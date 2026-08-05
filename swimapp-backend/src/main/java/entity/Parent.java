package entity;

import java.util.UUID;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="parents")
public class Parent {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="parent_id")
  private Long parentId;
  
  @Column(name="keycloak_user_id")
  private UUID keycloakUserId;
  
  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column(name="username")
  private String username;
  
  @Column(name="email")
  private String email;

  @OneToMany(mappedBy="parent")
  List<Swimmer> swimmers;

  // Getter and Setter
  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public UUID getKeyCloakUserId() {
    return keycloakUserId;
  }

  public void setKeycloakUserId(UUID keycloakUserId) {
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

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Swimmer> getSwimmers() {
    return swimmers;
  }

  public void setSwimmers(List<Swimmer> swimmers) {
    this.swimmers = swimmers;
  }
}
