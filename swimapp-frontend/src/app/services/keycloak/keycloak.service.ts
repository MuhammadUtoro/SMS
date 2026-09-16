import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';
import { AuthService } from '../auth/auth.service';
import { windowTime } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private keycloak = inject(Keycloak);
  //private authService = inject(AuthService);

  // Initializing keycloak at start up
  async init() {
    console.log("Initializing Keycloak!");
  }

  login() {
    this.keycloak.login({
      redirectUri: window.location.origin
    });
  }

  logout() {
    this.keycloak.logout({
      redirectUri: window.location.origin
    });
  }

  isLoggedIn() {
    return this.keycloak.authenticated;
  }

  getToken() {
    return this.keycloak.token;
  }
}
