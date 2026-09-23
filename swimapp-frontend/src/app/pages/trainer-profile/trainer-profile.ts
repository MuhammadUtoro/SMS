import { Component, OnInit, inject, signal } from '@angular/core';
import { TrainerService } from '../../services/trainer/trainer.service';
import { TrainerSummaryDto } from '../../interfaces/trainer-summary-dto';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-trainer-profile',
  imports: [ MatCardModule, MatDividerModule, MatButtonModule, RouterLink ],
  templateUrl: './trainer-profile.html',
  styleUrl: './trainer-profile.css',
})
export class TrainerProfile implements OnInit{

  private trainerService: TrainerService = inject(TrainerService);

  trainer = signal<TrainerSummaryDto | null>(null);

  ngOnInit(): void {
    this.trainerService.getMyProfile().subscribe({
      next: (trainer) => {
        this.trainer.set(trainer);
      },
      error: (error) => {
        console.log("Failed to load profile", error);
      }
    });
  }

}
