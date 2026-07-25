import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import Keycloak from 'keycloak-js';
import { KeycloakTokenParsed } from './interfaces/keycloak/keycloak-token-parsed';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private keycloak = inject(Keycloak);

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
