package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="level_requirements")
public class LevelRequirement {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="level_requirement_id")
  private Long levelRequirementId;

  @Column(name="requirements")
  private String requirement;

  @Column(name="description")
  private String description;

  // Since one requirement can only be in one level but one level 
  // can have multiple requirements, then from requirement perspective
  // it is ManyToOne relationship
  @ManyToOne
  @JoinColumn(name="level_id")
  private Level level;

  // Getter and setter
  public Long getLevelRequirementId() {
    return levelRequirementId;
  }

  public void setLevelRequirementId(Long levelRequirementId) {
    this.levelRequirementId = levelRequirementId;
  }

  public String getRequirement() {
    return requirement;
  }

  public void setRequirement(String requirement) {
    this.requirement = requirement;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }
}
