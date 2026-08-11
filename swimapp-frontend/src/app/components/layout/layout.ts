import { Component, inject } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'; 
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, RouterLink, MatToolbarModule, MatIconModule, MatButtonModule],
  templateUrl: './layout.html',
  styleUrl: './layout.css',
})
export class Layout {

  private keycloakService: KeycloakService = inject(KeycloakService);
  private authService: AuthService = inject(AuthService);
  user = this.authService.user;

  logout() {
    this.keycloakService.logout();
  }

}
