import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { MatButtonModule } from '@angular/material/button';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { CourseSummaryDto } from '../../interfaces/course-summary-dto';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { LevelRequirementDto } from '../../interfaces/level-requirement-dto';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { SwimmerService } from '../../services/swimmer/swimmer.service';
import { CourseService } from '../../services/course/course.service';
import { LevelService } from '../../services/level/level.service';
import { LevelRequirementService } from '../../services/level-requirement/level-requirement.service';
import { ParentDashboard } from '../../pages/dashboard/parent-dashboard/parent-dashboard';
import { TrainerDashboard } from '../../pages/dashboard/trainer-dashboard/trainer-dashboard';
import { AdminDashboard } from '../../pages/dashboard/admin-dashboard/admin-dashboard';

@Component({
  selector: 'app-dashboard',
  imports: [MatButtonModule, RouterLink, MatIconModule, MatCardModule, ParentDashboard, TrainerDashboard, AdminDashboard],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit{
  private authService: AuthService = inject(AuthService);
  private swimmerService: SwimmerService = inject(SwimmerService);
  private courseService: CourseService = inject(CourseService);
  private levelService: LevelService = inject(LevelService);
  private levelRequirementService: LevelRequirementService = inject(LevelRequirementService);

  user = this.authService.user;
  swimmersList = signal<SwimmerSummaryDto[]>([]);
  courses = signal<CourseSummaryDto[]>([]);
  levels = signal<LevelSummaryDto[]>([]);
  reqs = signal<LevelRequirementDto[]>([]);

  ngOnInit(): void {
    if (this.user()?.roles?.includes('ADMIN')) {
      this.getSwimmersList();
      this.getCoursesList();
      this.getLevelsList();
      this.getRequirementsList();
    }
  }

  getSwimmersList() {
    this.swimmerService.getSwimmersList().subscribe({
      next: (swimmersList) => {
        console.log("SWIMMERS: ", swimmersList);
        this.swimmersList.set(swimmersList);
      },
      error: error => {
        console.log("Failed to load swimmers", error);
      }
    });
  }

  getCoursesList() {
    this.courseService.getAllCourses().subscribe({
      next: (courses) => {
        this.courses.set(courses);
      },
      error: error => {
        console.log("Failed to load courses", error);
      }
    });
  }

  getLevelsList() {
    this.levelService.getAllLevels().subscribe({
      next: (levels) => {
        this.levels.set(levels);
      },
      error: error => {
        console.log("Failed to load levels", error);
      }
    });
  }

  getRequirementsList() {
    this.levelRequirementService.getAllRequirements().subscribe({
      next: (reqs) => {
        this.reqs.set(reqs);
      },
      error: error => {
        console.log("Failed to load requirements", error);
      }
    });
  }
}
