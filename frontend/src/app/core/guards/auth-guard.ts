import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    // Check if route has role requirements
    const requiredRoles = route.data['roles'] as Array<string>;
    if (requiredRoles && !authService.hasRole(requiredRoles)) {
      router.navigate(['/unauthorized']);
      return false;
    }
    return true;
  }

  // Not logged in so redirect to login page
  router.navigate(['/auth/login']);
  return false;
};
