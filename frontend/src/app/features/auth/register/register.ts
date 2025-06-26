import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService } from '../../../core/services/auth';
import { RegisterRequest, UserRole } from '../../../core/models/user.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    MatSnackBarModule
  ],
  templateUrl: './register.html',
  styleUrls: ['./register.scss']
})
export class RegisterComponent {
  registerForm: FormGroup;
  loading = false;
  userRoles = Object.values(UserRole);

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      role: ['', [Validators.required]],
      phoneNumber: [''],
      location: ['']
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.loading = true;
      const registerRequest: RegisterRequest = this.registerForm.value;
      
      this.authService.register(registerRequest).subscribe({
        next: (response) => {
          this.loading = false;
          this.snackBar.open('Registration successful! Please log in.', 'Close', { duration: 3000 });
          this.router.navigate(['/auth/login']);
        },
        error: (error) => {
          this.loading = false;
          const errorMessage = error.error?.message || 'Registration failed. Please try again.';
          this.snackBar.open(errorMessage, 'Close', { duration: 3000 });
        }
      });
    }
  }
}
