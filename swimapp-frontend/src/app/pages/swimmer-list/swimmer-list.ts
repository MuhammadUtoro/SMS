import { Component, inject, signal, OnInit } from '@angular/core';
import { SwimmerService } from '../../services/swimmer/swimmer.service';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-swimmer-list',
  imports: [RouterLink, MatIconModule],
  templateUrl: './swimmer-list.html',
  styleUrl: './swimmer-list.css',
})
export class SwimmerList implements OnInit{
  private swimmerService: SwimmerService = inject(SwimmerService);

  swimmers = signal<SwimmerSummaryDto[]>([]);

  ngOnInit(): void {
    this.getSwimmersList();
  }

  getSwimmersList(): void {
    this.swimmerService.getSwimmersList().subscribe({
      next: (swimmers) => {
        this.swimmers.set(swimmers);
      },
      error: (error) => {
        console.log('Failed to load swimmers!', error);
      }
    });
  }

}
