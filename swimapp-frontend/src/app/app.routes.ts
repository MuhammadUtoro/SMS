import { Routes } from '@angular/router';
import { Layout } from './components/layout/layout';
import { Home } from './pages/home/home';

export const routes: Routes = [
  {
    path: '',
    component: Home,
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: 'trainers/register',
        loadComponent: () =>
          import('./components/trainer-registration/trainer-registration').then(
            (m) => m.TrainerRegistration,
          ),
        title: 'Trainer Registration',
      },
      {
        path: 'courses',
        loadComponent: () =>
          import('./pages/course-form/course-form').then((m) => m.CourseForm),
        title: 'Create Course',
      },
      {
        path: 'course-list',
        loadComponent: () => import('./pages/course-list/course-list').then((m) => m.CourseList),
        title: 'All Courses',
      },
      {
        path: 'levels',
        loadComponent: () =>
          import('./pages/level-form/level-form').then((m) => m.LevelForm),
        title: 'Create Level',
      },
      {
        path: 'level-list',
        loadComponent: () => import('./pages/level-list/level-list').then((m) => m.LevelList),
        title: 'All levels'
      },
      {
        path: 'swimmers/:swimmerId',
        loadComponent: () => import('./pages/swimmer-details/swimmer-details').then((m) => m.SwimmerDetails),
        title: 'Swimmer Details'
      },
      {
        path: 'swimmer-list',
        loadComponent: () => import('./pages/swimmer-list/swimmer-list').then((m) => m.SwimmerList),
        title: 'All swimmers'
      },
      {
        path: 'swimmers/:swimmerId/course',
        loadComponent: () => import('./pages/assign-course/assign-course').then((m) => m.AssignCourse),
        title: 'Assign course'
      },
      {
        path: 'parents/me',
        loadComponent: () =>
          import('./components/parent-profile/parent-profile').then(
            (m) => m.ParentProfile,
          ),
        title: 'Parent Profile',
      },
      {
        path: 'parents/me/edit',
        loadComponent: () => import('./components/registration/registration').then((m) => m.Registration),
        title: 'Edit Profile'
      },
      {
        path: 'parents/create-swimmer',
        loadComponent: () =>
          import('./components/swimmer-registration/swimmer-registration').then(
            (m) => m.SwimmerRegistration,
          ),
        title: 'Swimmer Registration',
      },
      {
        path: 'swimmers',
        loadComponent: () =>
          import('./components/swimmer-registration/swimmer-registration').then(
            (m) => m.SwimmerRegistration,
          ),
        title: 'Swimmer Registration',
      },
      {
        path: 'trainers/me',
        loadComponent: () =>
          import('./components/trainer-profile/trainer-profile').then(
            (m) => m.TrainerProfile,
          ),
        title: 'Trainer Profile',
      },
      {
        path: 'trainers/me/edit',
        loadComponent: () =>
          import('./components/trainer-registration/trainer-registration').then((m) => m.TrainerRegistration),
        title: 'Edit Profile'
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
