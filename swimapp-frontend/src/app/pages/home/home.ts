import { Component, inject } from '@angular/core';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-home',
  imports: [RouterLink, MatIconModule, MatButtonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  private keycloakService: KeycloakService = inject(KeycloakService);

  login() {
    return this.keycloakService.login();
  }
}
