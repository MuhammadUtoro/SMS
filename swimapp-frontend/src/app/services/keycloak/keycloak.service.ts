import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
    
  private keycloak = inject(Keycloak);

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout();
  }

  isLoggedIn() {
    return this.keycloak.authenticated;
  }

  getToken() {
    return this.keycloak.token;
  }
}
