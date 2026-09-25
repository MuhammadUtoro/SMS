import { Component, inject, OnInit, signal } from '@angular/core';
import { SwimmerService } from '../../../services/swimmer/swimmer.service';
import { CourseService } from '../../../services/course/course.service';
import { LevelService } from '../../../services/level/level.service';
import { SwimmerSummaryDto } from '../../../interfaces/swimmer-summary-dto';
import { CourseSummaryDto } from '../../../interfaces/course-summary-dto';
import { LevelSummaryDto } from '../../../interfaces/level-summary-dto';

@Component({
  selector: 'app-admin-dashboard',
  imports: [],
  templateUrl: './admin-dashboard.html',
  styleUrl: './admin-dashboard.css',
})
export class AdminDashboard implements OnInit{
  private swimmerService: SwimmerService = inject(SwimmerService);
  private courseService: CourseService = inject(CourseService);
  private levelService: LevelService = inject(LevelService);

  swimmers = signal<SwimmerSummaryDto[]>([]);
  courses = signal<CourseSummaryDto[]>([]);
  levels = signal<LevelSummaryDto[]>([]);

  ngOnInit(): void {
    this.getSwimmersList();
    this.getCoursesList();
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
