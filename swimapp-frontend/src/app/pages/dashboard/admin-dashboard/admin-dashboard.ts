import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { SwimmerService } from '../../../services/swimmer/swimmer.service';
import { CourseService } from '../../../services/course/course.service';
import { SwimmerSummaryDto } from '../../../interfaces/swimmer-summary-dto';
import { CourseSummaryDto } from '../../../interfaces/course-summary-dto';
import { TrainerSummaryDto } from '../../../interfaces/trainer-summary-dto';
import { LevelSummaryDto } from '../../../interfaces/level-summary-dto';
import { TrainerService } from '../../../services/trainer/trainer.service';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { LevelService } from '../../../services/level/level.service';

@Component({
  selector: 'app-admin-dashboard',
  imports: [MatButtonModule, RouterLink],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css',
})
export class AdminDashboard implements OnInit {
  private swimmerService: SwimmerService = inject(SwimmerService);
  private courseService: CourseService = inject(CourseService);
  private trainerService: TrainerService = inject(TrainerService);
  private authService: AuthService = inject(AuthService);
  private levelService: LevelService = inject(LevelService);

  user = this.authService.user;
  swimmers = signal<SwimmerSummaryDto[]>([]);
  courses = signal<CourseSummaryDto[]>([]);
  trainers = signal<TrainerSummaryDto[]>([]);
  levels = signal<LevelSummaryDto[]>([]);

  ngOnInit(): void {
    this.getSwimmersList();
    this.getCoursesList();
    this.getTrainersList();
    this.getLevelsList();
  }


  getSwimmersList(): void {
    this.swimmerService.getSwimmersList().subscribe({
      next: (swimmers) => {
        this.swimmers.set(swimmers);
      },
      error: (error) => {
        console.log("Failed to load swimmers!", error);
      }
    });
  }

  getCoursesList(): void {
    this.courseService.getAllCourses().subscribe({
      next: (courses) => {
        this.courses.set(courses);
      },
      error: (error) => {
        console.log("Failed to load courses!", error);
      }
    });
  }

  getTrainersList(): void {
    this.trainerService.getAllTrainers().subscribe({
      next: (trainers) => {
        this.trainers.set(trainers);
      },
      error: (error) => {
        console.log("Failed to load trainers!", error);
      }
    });
  }

  getLevelsList(): void {
    this.levelService.getAllLevels().subscribe({
      next: (levels) => {
          this.levels.set(levels);
      },
      error: (error) => {
        console.log("Failed to load levels!", error);
      }
    });
  }
}
