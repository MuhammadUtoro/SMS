import { Component, inject, OnInit, signal } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { ParentService } from '../../services/parent/parent.service';
import { MatButtonModule } from '@angular/material/button';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { SwimmerService } from '../../services/swimmer/swimmer.service';

@Component({
  selector: 'app-dashboard',
  imports: [MatButtonModule, RouterLink, MatIconModule, MatCardModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit{
  private authService: AuthService = inject(AuthService);
  private parentService: ParentService = inject(ParentService);
  private swimmerService: SwimmerService = inject(SwimmerService);

  user = this.authService.user;
  swimmers: SwimmerSummaryDto[] = [];
  swimmersList = signal<SwimmerSummaryDto[]>([]);

  ngOnInit(): void {
    if (this.user()?.roles?.includes('PARENT')) {
      this.getMySwimmers();
    }
    if (this.user()?.roles?.includes('ADMIN')) {
      this.getSwimmersList();
    }
  }

  getMySwimmers() {
    this.parentService.getMySwimmers().subscribe({
      next: swimmers => {
        this.swimmers = swimmers;
        console.log(swimmers);
      },
      error: error => {
        console.log("Failed to load swimmers", error);
      }
    });
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
}
