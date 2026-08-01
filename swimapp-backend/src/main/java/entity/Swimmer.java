package entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="swimmers")
public class Swimmer {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="swimmer_id")
  private Long swimmerId;

  @ManyToOne
  @JoinColumn(name="parent_id")
  private Parent parent;
  
  @ManyToOne
  @JoinColumn(name="course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name="level_id")
  private Level level;

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column(name="date_of_birth")
  private LocalDate dateOfBirth;

  // Geter and Setter
  public Long getSwimmerId() {
    return swimmerId;
  }

  public void setSwimmerId(Long swimmerId) {
    this.swimmerId = swimmerId;
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

  public LocalDate getDob() {
    return dateOfBirth;
  }

  public void setDob(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Parent getParent() {
    return parent;
  }

  public void setParent(Parent parent) {
    this.parent = parent;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }
}
