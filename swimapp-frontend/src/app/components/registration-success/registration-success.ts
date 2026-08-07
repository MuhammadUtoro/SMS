import { Component, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-registration-success',
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './registration-success.html',
  styleUrl: './registration-success.css',
})
export class RegistrationSuccess {
  private keycloakService = inject(KeycloakService);

  login() {
    this.keycloakService.login();
  }
}
