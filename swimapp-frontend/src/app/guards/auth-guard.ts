import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { KeycloakService } from '../services/keycloak/keycloak.service';

export const authGuard: CanActivateFn = async() => {
  const keycloak = inject(KeycloakService);
  const loggedIn = await keycloak.isLoggedIn();

  if(loggedIn) {
    return true;
  }
  await keycloak.login();

  return false;
};
