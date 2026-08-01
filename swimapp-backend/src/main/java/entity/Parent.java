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

  public void setKeyCloakUserId(UUID keycloak_user_id) {
    this.keycloakUserId = keycloakUserId;
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
