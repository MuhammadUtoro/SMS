import { Component, signal, inject, OnInit } from '@angular/core';
import { ParentService } from '../../../services/parent/parent.service';
import { SwimmerSummaryDto } from '../../../interfaces/swimmer-summary-dto';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-parent-dashboard',
  imports: [MatDividerModule, MatButtonModule, MatIconModule, RouterLink],
  templateUrl: './parent-dashboard.html',
  styleUrl: './parent-dashboard.css',
})
export class ParentDashboard implements OnInit {
  private parentService: ParentService = inject(ParentService);
  swimmers = signal<SwimmerSummaryDto[]>([]);

  ngOnInit(): void {
      this.getMySwimmers();
  }

  getMySwimmers(): void {
    this.parentService.getMySwimmers().subscribe({
      next: (swimmers) => {
        this.swimmers.set(swimmers);
      },
      error: (error) => {
        console.log("Failed to load swimmers!", error);
      },
    });
  }

}
