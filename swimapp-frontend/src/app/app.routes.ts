import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'parents/register',
    loadComponent: () =>
      import('./components/registration/registration').then(
        (m) => m.Registration,
      ),
    title: 'Registration',
  },
  {
    path: 'registration-success',
    loadComponent: () => import('./components/registration-success/registration-success').then((m) => m.RegistrationSuccess),
    title: 'Registration Success'
  }
];
