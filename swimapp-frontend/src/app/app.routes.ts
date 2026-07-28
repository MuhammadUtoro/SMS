import { Routes } from '@angular/router';
import { Registration } from './components/registration/registration';

export const routes: Routes = [{
  path: 'parents/register',
  loadComponent: () =>
    import('./components/registration/registration').then((m) => m.Registration),
  title: 'Registration'
}];
