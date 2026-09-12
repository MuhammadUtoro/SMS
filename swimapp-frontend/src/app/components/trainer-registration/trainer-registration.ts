import { Component, ChangeDetectionStrategy, inject, signal, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TrainerService } from '../../services/trainer/trainer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trainer-registration',
  imports: [MatFormFieldModule, ReactiveFormsModule, MatInputModule, MatCardModule, MatButtonModule],
  templateUrl: './trainer-registration.html',
  styleUrl: './trainer-registration.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TrainerRegistration implements OnInit{
  private trainerService: TrainerService = inject(TrainerService);
  private router: Router = inject(Router);
  isEditMode = signal(this.router.url === '/trainers/me/edit');

  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
  });

  ngOnInit(): void {
      if (this.router.url === '/trainers/me/edit') {
        this.loadTrainerProfile();
    }
  }

  registerTrainer() {
    this.trainerService.registerTrainer(this.form.value).subscribe({
      next: (response) => {
        console.log('Success', response);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.log('Failed to register', error);
      }
    })
  }

  loadTrainerProfile() {
    this.trainerService.getMyProfile().subscribe({
      next: (trainer) => {
        this.form.patchValue({
          firstName: trainer.firstName,
          lastName: trainer.lastName,
        });
      },
      error: (error) => {
        console.log("Failed to load profile!", error);
      }
    });
  }
}
