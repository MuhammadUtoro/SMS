import { Component, inject, signal, OnInit } from '@angular/core';
import { LevelRequirementService } from '../../services/level-requirement/level-requirement.service';
import { LevelRequirementSummaryDto } from '../../interfaces/level-requirement-summary-dto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-requirement-summary',
  imports: [],
  templateUrl: './requirement-summary.html',
  styleUrl: './requirement-summary.css',
})
export class RequirementSummary implements OnInit {
  private levelRequirementService: LevelRequirementService = inject(LevelRequirementService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  summary = signal<LevelRequirementSummaryDto | undefined>(undefined);

  ngOnInit(): void {
      this.loadSummary();
  }

  loadSummary() {
    const requirementId = Number(
      this.route.snapshot.paramMap.get('requirementId')
    );

    this.levelRequirementService.getRequirementById(requirementId).subscribe({
      next: (summary) => {
        this.summary.set(summary);
      },
      error: (error) => {
        console.log("Failed to load summary!", error);
      }
    });
  }

}
