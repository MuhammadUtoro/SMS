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
  private Long course_id;

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
  private String course_name;

  @Column(name="course_day")
  private String course_day;

  @Column(name="course_time")
  private LocalTime course_time;
  
  // Getter and Setter
  public Long getCourseId() {
    return course_id;
  }

  public void setCourseId(Long course_id) {
    this.course_id = course_id;
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
    return course_name;
  }

  public void setCourseName(String course_name) {
    this.course_name = course_name;
  }

  public String getCourseDay() {
    return course_day;
  }

  public void setCourseDay(String course_day) {
    this.course_day = course_day;
  }

  public LocalTime getCourseTime() {
    return course_time;
  }
  
  public void setCourseTime(LocalTime course_time) {
    this.course_time = course_time;
  }

  public List<Swimmer> getSwimmers() {
    return swimmers;
  }

  public void setSwimmer(List<Swimmer> swimmers) {
    this.swimmers = swimmers;
  }
}
