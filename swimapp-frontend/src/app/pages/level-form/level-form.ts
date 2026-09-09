import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { LevelService } from '../../services/level/level.service';




@Component({
  selector: 'app-level-form',
  imports: [
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './level-form.html',
  styleUrl: './level-form.css',
})
export class LevelForm {
  private levelService: LevelService = inject(LevelService);
  private router: Router = inject(Router);
  form: FormGroup = new FormGroup({
    levelName: new FormControl(''),
  });

  createLevel() {
    this.levelService.createLevel(this.form.value).subscribe({
      next: (response) => {
        console.log('Success', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.log('Failed to create course', error);
      }
    })
  }
}
