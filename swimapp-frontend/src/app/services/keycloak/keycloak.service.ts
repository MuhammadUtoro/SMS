import { Injectable, inject } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
    
  private keycloak = inject(Keycloak);

  login() {
    this.keycloak.login();
  }
}
