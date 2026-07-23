package entity;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="levels")
public class Level {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="level_id")
  private Long level_id;

  private String level_name;

  @OneToMany(mappedBy="level")
  private List<LevelRequirement> requirements = new ArrayList<>();

  // ManyToOne relationship with course
  @OneToMany(mappedBy="level")
  private List<Course> courses = new ArrayList<>();

  // OneToMany relationship wih swimmer
  @OneToMany(mappedBy="level")
  private List<Swimmer> swimmers = new ArrayList<>();

  // Getter and Setter
  public Long getLevelId() {
    return level_id;
  }

  public void setLevelId(Long level_id) {
    this.level_id = level_id;
  }

  public String getLevelName() {
    return level_name;
  }

  public void setLevelName(String level_name) {
    this.level_name = level_name;
  }

  public List<LevelRequirement> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<LevelRequirement> requirements) {
    this.requirements = requirements;
  }

  public List<Course> getCourse() {
    return courses;
  }

  public void setCourse(List<Course> courses) {
    this.courses = courses;
  }

  public List<Swimmer> getSwimmers() {
    return swimmers;
  }

  public void setSwimmer(List<Swimmer> swimmers) {
    this.swimmers = swimmers;
  }
}
