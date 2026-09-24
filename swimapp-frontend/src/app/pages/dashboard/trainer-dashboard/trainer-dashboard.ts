import { Component, OnInit, inject, signal } from '@angular/core';
import { TrainerService } from '../../../services/trainer/trainer.service';
import { TrainerSummaryDto } from '../../../interfaces/trainer-summary-dto';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-trainer-dashboard',
  imports: [MatDividerModule],
  templateUrl: './trainer-dashboard.html',
  styleUrl: './trainer-dashboard.css',
})
export class TrainerDashboard implements OnInit{
  private trainerService: TrainerService = inject(TrainerService);
  trainer = signal<TrainerSummaryDto | null>(null);

  ngOnInit(): void {
    this.trainerService.getMyProfile().subscribe({
      next: (trainer) => {
        this.trainer.set(trainer);
      },
      error: (error) => {
        console.log("Failed to load profile!", error);
      }
    });
  }
}
