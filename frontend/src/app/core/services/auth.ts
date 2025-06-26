import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { AuthResponse, LoginRequest, RegisterRequest, User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(private http: HttpClient) {
    const storedUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<User | null>(
      storedUser ? JSON.parse(storedUser) : null
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/auth/login`, loginRequest)
      .pipe(map(response => {
        // Store user details and jwt token in local storage
        localStorage.setItem('token', response.token);
        const user: User = {
          id: response.id,
          email: response.email,
          firstName: response.firstName,
          lastName: response.lastName,
          role: response.role as any
        };
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.currentUserSubject.next(user);
        return response;
      }));
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/register`, registerRequest);
  }

  logout() {
    // Remove user from local storage and set current user to null
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return token != null && !this.isTokenExpired(token);
  }

  private isTokenExpired(token: string): boolean {
    try {
      const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
      return (Math.floor((new Date).getTime() / 1000)) >= expiry;
    } catch (Error) {
      return false;
    }
  }

  hasRole(roles: string[]): boolean {
    const user = this.currentUserValue;
    return user ? roles.includes(user.role) : false;
  }
}
