import { Component, signal, OnInit, inject } from '@angular/core';
import { TrainerService } from '../../services/trainer/trainer.service';
import { TrainerSummaryDto } from '../../interfaces/trainer-summary-dto';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-trainer-list',
  imports: [RouterLink, MatIconModule],
  templateUrl: './trainer-list.html',
  styleUrl: './trainer-list.css',
})
export class TrainerList implements OnInit {
  private trainerService: TrainerService = inject(TrainerService);
  trainers = signal<TrainerSummaryDto[]>([]);

  ngOnInit(): void {
     this.getTrainersList();
  }

  getTrainersList(): void {
    this.trainerService.getAllTrainers().subscribe({
      next: (trainers) => {
        this.trainers.set(trainers);
      },
      error: (error) => {
        console.log("Failed to load trainers!", error);
      },
    });
  }
}
