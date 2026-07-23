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
  private Long swimmer_id;

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
  private String first_name;

  @Column(name="last_name")
  private String last_name;

  @Column(name="date_of_birth")
  private LocalDate date_of_birth;

  // Geter and Setter
  public Long getSwimmerId() {
    return swimmer_id;
  }

  public void setSwimmerId(Long swimmer_id) {
    this.swimmer_id = swimmer_id;
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
    this.last_name = last_name;
  }

  public LocalDate getDob() {
    return date_of_birth;
  }

  public void setDob(LocalDate date_of_birth) {
    this.date_of_birth = date_of_birth;
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
