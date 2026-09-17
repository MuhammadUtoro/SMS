import { Component, signal, OnInit, inject } from '@angular/core';
import { SwimmerDetailDto } from '../../interfaces/swimmer-detail-dto';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { SwimmerService } from '../../services/swimmer/swimmer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-swimmer-details',
  imports: [],
  templateUrl: './swimmer-details.html',
  styleUrl: './swimmer-details.css',
})
export class SwimmerDetails implements OnInit {
  private swimmerService: SwimmerService = inject(SwimmerService);
  private route: ActivatedRoute = inject(ActivatedRoute);
  swimmerDetails = signal<SwimmerDetailDto | undefined>(undefined);
  swimmerSummary = signal<SwimmerSummaryDto | undefined>(undefined);

  ngOnInit(): void {
    const swimmerId = Number(
      this.route.snapshot.paramMap.get('swimmerId')
    );
    this.getSwimmerDetails(swimmerId);
  }

 getSwimmerDetails(swimmerId: number): void {
    this.swimmerService.getSwimmerById(swimmerId).subscribe({
      next: (swimmer) => {
        this.swimmerDetails.set(swimmer);
      },
      error: (error) => {
        console.log("Failed to load swimmer", error);
      },
    });
  }
}
