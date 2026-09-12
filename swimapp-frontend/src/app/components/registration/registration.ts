import { Component, ChangeDetectionStrategy, inject, OnInit, signal } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { ParentService } from '../../services/parent/parent.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registration',
  imports: [
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './registration.html',
  styleUrl: './registration.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Registration implements OnInit{
  private parentService: ParentService = inject(ParentService);
  private keycloakService: KeycloakService = inject(KeycloakService);
  private router: Router = inject(Router);
  private route: ActivatedRoute = inject(ActivatedRoute);

  isEditMode = signal(this.router.url === '/parents/me/edit');

  form: FormGroup = new FormGroup({
    email: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
  });

  ngOnInit() {
    if (this.router.url === '/parents/me/edit') {
      this.loadParentProfile();
    }
  }

  // Method to register parent
  registerParent() {
    this.parentService.registerParent(this.form.value).subscribe({
      next: (response) => {
        console.log('Success!', response);
        this.router.navigate(['registration-success']);
      },
      error: (error) => {
        console.error('Failed!', error);
      },
    });
  }

  // To login user after registration
  login() {
    this.keycloakService.login();
  }

  loadParentProfile() {
    this.parentService.getMyProfile().subscribe({
      next: (parent) => {
        this.form.patchValue({
          email: parent.email,
          firstName: parent.firstName,
          lastName: parent.lastName,
          username: parent.username
        });
      },
      error: (error) => {
        console.log("Failed to load profile!", error);
      }
    });
  }
}
