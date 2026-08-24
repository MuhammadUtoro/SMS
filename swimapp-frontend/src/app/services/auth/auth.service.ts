import { Injectable, inject, effect, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { KeycloakEventType, KEYCLOAK_EVENT_SIGNAL } from 'keycloak-angular';

export interface AuthenticatedUser {
  username: string;
  keycloak_user_id: string;
  roles: string[];
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  private readonly keycloakEvent = inject(KEYCLOAK_EVENT_SIGNAL);
  private currentUser = signal<AuthenticatedUser | null>(null);
  readonly user = this.currentUser.asReadonly();

  constructor() {
    effect(() => {
      const event = this.keycloakEvent();
      console.log("Keycloak event: ", event);
      if (KeycloakEventType.AuthSuccess) {
        console.log("Keycloak is ready and authenticated!");
        this.loadAndNavigate();
      }
    })
  }
  getCurrentUser() {
    return this.http.get<AuthenticatedUser>('http://localhost:8080/auth/me');
  }

  loadAndNavigate() {
    this.getCurrentUser().subscribe({
      next: (user) => {
        this.currentUser.set(user);
        if (user.roles.includes('PARENT')) {
          this.router.navigate(['/dashboard']);
        } else if (user.roles.includes('TRAINER')) {
          this.router.navigate(['/dashboard']);
        } else if (user.roles.includes('ADMIN')) {
          this.router.navigate(['/dashboard']);
        }
      },
      error: (error) => {
        console.error('Failed to load authenticated user!', error);
      },
    });
  }
}
