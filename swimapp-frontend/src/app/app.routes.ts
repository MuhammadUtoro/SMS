import { Routes } from '@angular/router';
import { Layout } from './components/layout/layout';

export const routes: Routes = [
  {
    path: '',
    component: Layout,
    children: [
      {
        path: 'parents/me',
        loadComponent: () =>
          import('./components/parent-profile/parent-profile').then(
            (m) => m.ParentProfile,
          ),
        title: 'Parent Profile',
      },
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./components/dashboard/dashboard').then((m) => m.Dashboard),
        title: 'Dashboard',
      },
    ],
  },
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
    loadComponent: () =>
      import('./components/registration-success/registration-success').then(
        (m) => m.RegistrationSuccess,
      ),
    title: 'Registration Success',
  },
];
