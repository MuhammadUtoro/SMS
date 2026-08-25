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
        path: 'trainers/me',
        loadComponent: () =>
          import(
            './components/trainer-profile/trainer-profile'
          ).then((m) => m.TrainerProfile,)
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
  {
    path: 'parents/create-swimmer',
    loadComponent: () =>
      import('./components/swimmer-registration/swimmer-registration').then((m) => m.SwimmerRegistration),
    title: "Swimmer Registration"
  },
  {
    path: 'trainers/register',
    loadComponent: () => import (
      './components/trainer-registration/trainer-registration'
    ).then((m) => m.TrainerRegistration),
      title: 'Trainer Registration'
  }
];
