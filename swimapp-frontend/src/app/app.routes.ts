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
  },
  {
    path: 'parents/me',
    loadComponent: () => import(
      './components/parent-profile/parent-profile'
    ).then((m) => m.ParentProfile),
    title: "Parent Profile"
  }
];
