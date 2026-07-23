import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import Keycloak from 'keycloak-js';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private keycloak = inject(Keycloak);

  login() {
    this.keycloak.login();
  }

  logout() {
    this.keycloak.logout();
  }

  profile() {
    console.log(this.keycloak.profile);
  }

  token() {
    console.log(this.keycloak.token);
  }
}
