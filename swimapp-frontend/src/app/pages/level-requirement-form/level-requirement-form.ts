import { Component, ChangeDetectionStrategy, inject, signal } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { LevelRequirementService } from '../../services/level-requirement/level-requirement.service';
import { LevelService } from '../../services/level/level.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LevelSummaryDto } from '../../interfaces/level-summary-dto';
import { CreateLevelRequirementDto } from '../../interfaces/create-level-requirement-dto';


@Component({
  selector: 'app-level-requirement-form',
  imports: [
    ReactiveFormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule
  ],
  templateUrl: './level-requirement-form.html',
  styleUrl: './level-requirement-form.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LevelRequirementForm {
  private levelRequirementService: LevelRequirementService = inject(LevelRequirementService);
  private levelService: LevelService = inject(LevelService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  private router: Router = inject(Router);

  levelSummary = signal<LevelSummaryDto | undefined>(undefined);

  form: FormGroup = new FormGroup({
    requirement: new FormControl(''),
    description: new FormControl(''),
  })

  loadLevelSummary(levelId: number) {
    this.levelService.getLevelById(levelId).subscribe({
      next: (level) => {
        this.levelSummary.set(level);
      },
      error: (error) => {
        console.log("Failed to load summary!", error);
      }
    });
  }

  createRequirement(): void {
    const levelId = Number(
      this.route.snapshot.paramMap.get("levelId")
    );

    const dto: CreateLevelRequirementDto = {
      levelId: levelId,
      requirement: this.form.value.requirement,
      description: this.form.value.description
    }

    this.levelRequirementService.createRequirement(dto).subscribe({
      next: () => {
        this.router.navigate(['/level-list']);
      },
      error: (error) => {
        console.log("Failed to add requirement!", error);
      }
    });
  }

}
