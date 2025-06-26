import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login';
import { RegisterComponent } from './features/auth/register/register';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: '/auth/login', pathMatch: 'full' },
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/register', component: RegisterComponent },
  { 
    path: 'dashboard', 
    loadComponent: () => import('./features/public/dashboard/dashboard').then(c => c.DashboardComponent),
    canActivate: [authGuard]
  },
  { path: '**', redirectTo: '/auth/login' }
];
