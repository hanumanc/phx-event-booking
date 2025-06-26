export interface User {
  id?: string;
  email: string;
  firstName: string;
  lastName: string;
  role: UserRole;
  phoneNumber?: string;
  location?: string;
  verified?: boolean;
  createdAt?: Date;
  updatedAt?: Date;
}

export enum UserRole {
  VENDOR = 'VENDOR',
  ADMIN = 'ADMIN',
  PUBLIC_USER = 'PUBLIC_USER'
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: UserRole;
  phoneNumber?: string;
  location?: string;
}

export interface AuthResponse {
  token: string;
  type: string;
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  role: string;
}