import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { ParentService } from '../../services/parent/parent.service';

@Component({
  selector: 'app-registration',
  imports: [
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule
  ],
  templateUrl: './registration.html',
  styleUrl: './registration.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Registration {
  private parentService = inject(ParentService);

  form: FormGroup = new FormGroup({
    email: new FormControl('email'),
    firstName: new FormControl('firstName'),
    lastName: new FormControl('lastName'),
    username: new FormControl('username'),
    password: new FormControl('password'),
  });

  registerParent() {
    this.parentService.registerParent(this.form.value).subscribe({
      next: (response) => {
        console.log('Success!', response);
      },
      error: (error) => {
        console.error('Failed!', error);
      },
    });
  }
}
