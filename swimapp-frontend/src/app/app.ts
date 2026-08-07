import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import Keycloak from 'keycloak-js';
import { HttpClient } from '@angular/common/http';
import { KeycloakTokenParsed } from './interfaces/keycloak/keycloak-token-parsed';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatButtonModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  private http = inject(HttpClient);
  private keycloak = inject(Keycloak);

  callBackend() {
    this.http.get('http://localhost:8080/auth/me').subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  // Triggers keycloak login
  login() {
    this.keycloak.login();
  }

  // Triggers keycloak logout
  logout() {
    this.keycloak.logout();
  }

  // Look at the user's profile
  profile() {
    console.log(this.keycloak.profile);
  }
  // Returns the token - if available
  token() {
    console.log(this.keycloak.token);
  }

  // Get the username - if authenticated
  getUsername(): string {
    console.log(this.keycloak.tokenParsed?.['preferred_username']);
    return this.keycloak.tokenParsed?.['preferred_username'] || '';
  }
}
