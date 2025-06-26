# Event Booking Platform

A comprehensive event management and booking system built with Angular, Spring Boot, and MongoDB. This platform connects event vendors with attendees, providing a seamless experience for event creation, discovery, and ticket booking.

## 🚀 Features

### Multi-User Platform
- **Vendors**: Create and manage events, view booking analytics
- **Admins**: Approve/reject events, manage users, system oversight  
- **Public Users**: Search events, make reservations, manage bookings

### Core Functionality
- User registration and authentication with JWT
- Role-based access control (RBAC)
- Event creation and management
- Event approval workflow
- Real-time event search and filtering
- Responsive design for mobile compatibility

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: MongoDB
- **Security**: Spring Security with JWT
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven

### Frontend
- **Framework**: Angular 17+
- **UI Library**: Angular Material
- **State Management**: RxJS
- **Styling**: SCSS
- **Build Tool**: npm

### DevOps
- **Version Control**: Git
- **Containerization**: Docker
- **CI/CD**: GitHub Actions

## 📋 Prerequisites

- Java 17 or higher
- Node.js 18.x or higher
- MongoDB 6.0 or higher
- Maven 3.8+
- Git

## 🔧 Installation

### Clone the Repository
```bash
git clone https://github.com/hanumanc/phx-event-booking
cd phx-event-booking
```

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Configure MongoDB connection in `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/event_booking_db
spring.data.mongodb.database=event_booking_db
```

3. Install dependencies and run:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
ng serve
```

The frontend will start on `http://localhost:4200`

### Using Docker (Alternative)

1. Build and run with Docker Compose:
```bash
docker-compose up --build
```

This will start:
- MongoDB on port 27017
- Backend on port 8080
- Frontend on port 4200

## 🏗️ Project Structure

```
event-booking-platform/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/eventbooking/
│   │   │   │       ├── config/       # Configuration classes
│   │   │   │       ├── controller/   # REST controllers
│   │   │   │       ├── dto/          # Data Transfer Objects
│   │   │   │       ├── entity/       # MongoDB entities
│   │   │   │       ├── exception/    # Custom exceptions
│   │   │   │       ├── repository/   # MongoDB repositories
│   │   │   │       ├── security/     # Security configuration
│   │   │   │       ├── service/      # Business logic
│   │   │   │       └── util/         # Utility classes
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-dev.properties
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/                 # Core module (services, guards)
│   │   │   ├── shared/               # Shared components
│   │   │   ├── features/             # Feature modules
│   │   │   │   ├── auth/
│   │   │   │   ├── vendor/
│   │   │   │   ├── events/
│   │   │   │   ├── booking/
│   │   │   │   ├── admin/
│   │   │   │   └── public/
│   │   │   └── app-routing.module.ts
│   │   ├── assets/
│   │   ├── environments/
│   │   └── styles.scss
│   ├── angular.json
│   └── package.json
├── docker-compose.yml
└── README.md
```

## 🔑 Environment Variables

### Backend (application.properties)
```properties
# Database
MONGODB_URI=mongodb://localhost:27017/event_booking_db
JWT_SECRET=your-secret-key
CORS_ALLOWED_ORIGINS=http://localhost:4200

# Security
jwt.secret=${JWT_SECRET:defaultSecretKey}
jwt.expiration=86400000
```

### Frontend (environment.ts)
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

## 📚 API Documentation

Once the backend is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Main API Endpoints

#### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login

#### Health Check
- `GET /api/health` - Application health status

#### User Management
- `GET /api/users/profile` - Get user profile (authenticated)

#### Events (Public)
- `GET /api/public/events` - List all approved events
- `GET /api/public/events/{id}` - Get event details
- `GET /api/public/events/search` - Search events
- `GET /api/public/events/location` - Filter by location

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
ng test
```

## 🚀 Quick Start Guide

### Option 1: Docker (Recommended)
```bash
git clone https://github.com/hanumanc/phx-event-booking
cd phx-event-booking
docker-compose up --build
```

Access at: http://localhost:4200

### Option 2: Manual Setup
1. Start MongoDB locally
2. Run backend: `cd backend && mvn spring-boot:run`  
3. Run frontend: `cd frontend && ng serve`

## 🔒 Security Features

- JWT-based authentication
- Role-based access control (RBAC)
- Password encryption with BCrypt
- CORS configuration
- Route guards in Angular
- HTTP interceptors for token management

## 📝 Database Schema

### User Collection
```json
{
  "id": "string",
  "email": "string (unique)",
  "password": "string (encrypted)",
  "firstName": "string",
  "lastName": "string", 
  "role": "VENDOR | ADMIN | PUBLIC_USER",
  "phoneNumber": "string",
  "location": "string",
  "verified": "boolean",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### Event Collection
```json
{
  "id": "string",
  "title": "string",
  "description": "string",
  "vendorId": "string",
  "category": "string",
  "location": "string",
  "venue": "string",
  "eventDate": "datetime",
  "startTime": "datetime", 
  "endTime": "datetime",
  "ticketPrice": "decimal",
  "maxAttendees": "integer",
  "currentAttendees": "integer",
  "status": "PENDING | APPROVED | REJECTED | CANCELLED | COMPLETED",
  "imageUrl": "string",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

## 📊 Project Status

Current Version: 1.0.0-SNAPSHOT

### Completed Features ✅
- [x] User authentication and authorization
- [x] Multi-role user registration (Vendor, Admin, Public User)
- [x] JWT token management with role-based access control
- [x] Angular frontend with Material Design
- [x] Spring Boot backend with MongoDB integration
- [x] Basic event creation and management entities
- [x] Public event discovery API endpoints
- [x] Role-based route protection
- [x] Docker containerization setup
- [x] API documentation with Swagger/OpenAPI
- [x] Health check endpoints
- [x] Global exception handling
- [x] CORS configuration for frontend communication
- [x] Responsive login/register components

### Framework Capabilities
- **Authentication**: Complete JWT-based auth flow
- **Authorization**: Role-based access control (VENDOR, ADMIN, PUBLIC_USER)
- **Database**: MongoDB with Spring Data repositories
- **Frontend**: Angular 17+ with Material UI components
- **API**: RESTful endpoints with Swagger documentation
- **Security**: Spring Security with encrypted passwords
- **Testing**: Unit test framework setup
- **DevOps**: Docker Compose for easy deployment

### Next Development Phase 🔄
- [ ] Event approval workflow (Admin dashboard)
- [ ] Booking system implementation
- [ ] Payment gateway integration
- [ ] File upload for event images
- [ ] Email notification system
- [ ] Advanced search and filtering
- [ ] Analytics dashboard for vendors
- [ ] Real-time updates with WebSocket

## 🛠️ Development Guidelines

### Code Style
- Java: Follow Spring Boot best practices
- TypeScript/Angular: Follow Angular style guide
- Use meaningful variable and method names
- Add comments for complex business logic

### Git Workflow
- Use feature branches for new functionality
- Write descriptive commit messages
- Create pull requests for code review

## 🐛 Troubleshooting

### Common Issues

1. **MongoDB Connection Error**:
   - Ensure MongoDB is running
   - Check connection string in application.properties
   - Use test profile: `mvn spring-boot:run -Dspring-boot.run.profiles=test`

2. **Angular Build Errors**:
   - Clear node_modules: `rm -rf node_modules && npm install`
   - Check Angular CLI version: `ng version`

3. **CORS Issues**:
   - Verify CORS_ALLOWED_ORIGINS in backend configuration
   - Check browser developer tools for CORS errors

## 👥 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Made with ❤️ by the Event Booking Platform Team

A comprehensive event management and booking system built with Angular, Spring Boot, and MongoDB. This platform connects event vendors with attendees, providing a seamless experience for event creation, discovery, and ticket booking.

## 🚀 Features

### For Vendors
- Create and manage events with detailed information
- Upload event images and manage galleries
- Configure multiple ticket types and pricing
- Track bookings and revenue
- Manage business profile and documentation

### For Users
- Browse and search events by category, location, and date
- Book tickets with secure checkout process
- Manage booking history
- Download e-tickets with QR codes
- Create and manage user profiles

### For Administrators
- Review and approve vendor registrations
- Moderate event listings
- Access platform analytics and reports
- Manage user accounts
- Configure platform settings

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: MongoDB
- **Security**: Spring Security with JWT
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven

### Frontend
- **Framework**: Angular 17+
- **UI Library**: Angular Material
- **State Management**: RxJS
- **Styling**: SCSS
- **Build Tool**: npm

### DevOps
- **Version Control**: Git
- **Containerization**: Docker
- **CI/CD**: GitHub Actions

## 📋 Prerequisites

- Java 17 or higher
- Node.js 18.x or higher
- MongoDB 6.0 or higher
- Maven 3.8+
- Git

## 🔧 Installation

### Clone the Repository
```bash
git clone https://github.com/hanumanc/phx-event-booking
cd phx-event-booking
```



### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Configure MongoDB connection in `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/event_booking_db
spring.data.mongodb.database=event_booking_db
```

3. Install dependencies and run:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Run the development server:
```bash
ng serve
```

The frontend will start on `http://localhost:4200`

### Using Docker (Alternative)

1. Build and run with Docker Compose:
```bash
docker-compose up --build
```

This will start:
- MongoDB on port 27017
- Backend on port 8080
- Frontend on port 4200

## 🏗️ Project Structure

```
event-booking-platform/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/eventbooking/
│   │   │   │       ├── config/       # Configuration classes
│   │   │   │       ├── controller/   # REST controllers
│   │   │   │       ├── dto/          # Data Transfer Objects
│   │   │   │       ├── entity/       # MongoDB entities
│   │   │   │       ├── exception/    # Custom exceptions
│   │   │   │       ├── repository/   # MongoDB repositories
│   │   │   │       ├── security/     # Security configuration
│   │   │   │       ├── service/      # Business logic
│   │   │   │       └── util/         # Utility classes
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-dev.properties
│   │   └── test/
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── core/                 # Core module (services, guards)
│   │   │   ├── shared/               # Shared components
│   │   │   ├── features/             # Feature modules
│   │   │   │   ├── auth/
│   │   │   │   ├── vendor/
│   │   │   │   ├── events/
│   │   │   │   ├── booking/
│   │   │   │   ├── admin/
│   │   │   │   └── public/
│   │   │   └── app-routing.module.ts
│   │   ├── assets/
│   │   ├── environments/
│   │   └── styles.scss
│   ├── angular.json
│   └── package.json
├── docker-compose.yml
└── README.md
```

## 🔑 Environment Variables

### Backend (.env)
```
MONGODB_URI=mongodb://localhost:27017/event_booking_db
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000
UPLOAD_DIR=./uploads
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USERNAME=your-email@gmail.com
SMTP_PASSWORD=your-password
```

### Frontend (environment.ts)
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api',
  uploadUrl: 'http://localhost:8080/uploads'
};
```

## 📚 API Documentation

Once the backend is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

### Main API Endpoints

#### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login
- `POST /api/auth/refresh` - Refresh JWT token

#### Events
- `GET /api/public/events` - List all approved events
- `GET /api/public/events/{id}` - Get event details
- `POST /api/events` - Create new event (Vendor only)
- `PUT /api/events/{id}` - Update event (Vendor only)

#### Bookings
- `POST /api/bookings` - Create new booking
- `GET /api/bookings` - Get user bookings
- `GET /api/bookings/{id}` - Get booking details

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
ng test
```

### E2E Tests
```bash
cd frontend
ng e2e
```

## 🚀 Deployment

### Production Build

#### Backend
```bash
cd backend
mvn clean package
java -jar target/event-booking-platform-1.0.0.jar
```

#### Frontend
```bash
cd frontend
ng build --prod
```

The build artifacts will be stored in the `dist/` directory.

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards

- Follow Java naming conventions for backend
- Follow Angular style guide for frontend
- Write unit tests for all new features
- Ensure all tests pass before submitting PR
- Update documentation as needed

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Support

For support, email support@eventbookingplatform.com or open an issue in the GitHub repository.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Angular team for the powerful frontend framework
- MongoDB team for the flexible database
- All contributors who help improve this platform

## 📊 Project Status

Current Version: 1.0.0-SNAPSHOT

### Completed Features
- ✅ User authentication and authorization
- ✅ Vendor registration and management
- ✅ Event creation and management
- ✅ Admin approval workflow
- ✅ Booking system
- ✅ Public event discovery

### Upcoming Features
- 🔄 Payment gateway integration
- 🔄 Email notifications
- 🔄 Advanced analytics dashboard
- 🔄 Mobile application
- 🔄 Multi-language support

---

Made with ❤️ by the Event Booking Platform Team