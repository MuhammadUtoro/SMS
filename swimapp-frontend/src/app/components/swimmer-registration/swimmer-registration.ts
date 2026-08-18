import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { SwimmerService } from '../../services/swimmer/swimmer.service';
import { provideNativeDateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-swimmer-registration',
  imports: [
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule
  ],
  templateUrl: './swimmer-registration.html',
  styleUrl: './swimmer-registration.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [provideNativeDateAdapter()],
})
export class SwimmerRegistration {
  private swimmerService = inject(SwimmerService);
  private router = inject(Router);

  form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    dateOfBirth: new FormControl<Date | null>(null),
  });

  // Method to create swimmer
  createSwimmer() {
    const date = this.form.value.dateOfBirth;
    const request = {
      ...this.form.value,
      dateOfBirth: date? this.formatDate(date) : null
    }

    this.swimmerService.createSwimmer(request).subscribe({
      next: (response) => {
        console.log("Success!", response);
        this.router.navigate(['dashboard']);
      },
      error: (error) => {
        console.error("Failed!", error);
      }
    })
  }
  private formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
  }
}
