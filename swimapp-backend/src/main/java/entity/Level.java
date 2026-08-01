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
  private Long levelId;
    
  @Column(name="level_name")
  private String levelName;

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
    return levelId;
  }

  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }

  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
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
