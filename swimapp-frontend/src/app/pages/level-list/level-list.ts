import { Component, inject, signal, OnInit } from '@angular/core';
import { LevelService } from '../../services/level/level.service';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { LevelRequirementDto } from '../../interfaces/level-requirement-dto';
import { RouterLink, Router, ActivatedRoute } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { LevelRequirementService } from '../../services/level-requirement/level-requirement.service';

@Component({
  selector: 'app-level-list',
  imports: [
    RouterLink,
    MatButtonModule
  ],
  templateUrl: './level-list.html',
  styleUrl: './level-list.css',
})
export class LevelList implements OnInit{
  private levelService: LevelService = inject(LevelService);
  private levelRequirementService: LevelRequirementService = inject(LevelRequirementService);
  private router: Router = inject(Router);
  levels = signal<LevelSummaryDto[]>([]);

  ngOnInit(): void {
    this.getLevelsList();
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

  deleteRequirement(requirementId: number): void {
    this.levelRequirementService.deleteRequirement(requirementId).subscribe({
      next: () => {
        this.getLevelsList();
        console.log("Successfully deleted!");
      },
      error: (error) => {
        console.log("Failed to delete!", error);
      }
    });
  }
}
