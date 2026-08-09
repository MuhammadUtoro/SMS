import { Component, OnInit, inject, signal } from '@angular/core';
import { ParentService } from '../../services/parent/parent.service';
import { ParentSummaryDto } from '../../interfaces/parent-summary-dto';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';


@Component({
  selector: 'app-parent-profile',
  imports: [ MatCardModule, MatDividerModule, MatButtonModule ],
  templateUrl: './parent-profile.html',
  styleUrl: './parent-profile.css',
})
export class ParentProfile implements OnInit{

  private parentService: ParentService = inject(ParentService);

  parent = signal<ParentSummaryDto | null>(null);

  ngOnInit() {
    this.parentService.getMyProfile().subscribe({
      next: (parent) => {
        console.log("My profile: ", parent);
        this.parent.set(parent);
      },
      error: (error) => {
        console.log("Failed to load profile: ", error);
      },
    });
  }
}
