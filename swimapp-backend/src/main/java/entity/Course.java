package entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="course_id")
  private Long courseId;

  // It is a ManyToOne relationship from course perspective, since one course
  // can only have one trainers but one trainer can be on multiple courses 
  @ManyToOne
  @JoinColumn(name="trainer_id")
  private Trainer trainer;

  // Now the relationship with level. One course can only have one level and
  // one level can be on multiple courses. So, from the course perspective it
  // is ManyToOne relationship
  @ManyToOne
  @JoinColumn(name="level_id")
  private Level level;

  @OneToMany(mappedBy="course")
  private List<Swimmer> swimmers;

  @Column(name="course_name")
  private String courseName;

  @Column(name="course_day")
  private String courseDay;

  @Column(name="course_time")
  private LocalTime courseTime;
  
  // Getter and Setter
  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public Trainer getTrainer() {
    return trainer;
  }

  public void setTrainer(Trainer trainer) {
    this.trainer = trainer;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getCourseDay() {
    return courseDay;
  }

  public void setCourseDay(String courseDay) {
    this.courseDay = courseDay;
  }

  public LocalTime getCourseTime() {
    return courseTime;
  }
  
  public void setCourseTime(LocalTime courseTime) {
    this.courseTime = courseTime;
  }

  public List<Swimmer> getSwimmers() {
    return swimmers;
  }

  public void setSwimmer(List<Swimmer> swimmers) {
    this.swimmers = swimmers;
  }
}
