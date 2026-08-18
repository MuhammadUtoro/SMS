import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { ParentService } from '../../services/parent/parent.service';
import { MatButtonModule } from '@angular/material/button';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';

@Component({
  selector: 'app-dashboard',
  imports: [MatButtonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit{
  private authService: AuthService = inject(AuthService);
  private parentService: ParentService = inject(ParentService);

  user = this.authService.user;
  swimmers: SwimmerSummaryDto[] = [];

  ngOnInit(): void {
    this.getMySwimmers();
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
    })
  }
}
