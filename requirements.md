# Java Training Project: Multi-User Event Booking Platform

## Project Overview
Build a comprehensive event booking platform with three user types: **Vendors** (event creators), **Admins** (event approvers), and **Public Users** (event attendees). The platform uses microservices architecture, React frontend, and MongoDB database with role-based access control and event approval workflow.

## Technology Stack
- **Backend**: Java 17+, Spring Boot 3.x, Spring Security, Spring Data MongoDB
- **Frontend**: React 18+, Axios, React Router, Material-UI/Ant Design
- **Database**: MongoDB
- **Authentication**: JWT + OAuth2 with Role-Based Access Control (RBAC)
- **Deployment**: Docker, Docker Compose
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito, TestContainers

## User Types & Roles
1. **Vendors**: Create and manage events, view booking analytics
2. **Admins**: Approve/reject events, manage users, system oversight
3. **Public Users**: Search events, make reservations, manage bookings

## Architecture
Microservices architecture with API Gateway and role-based routing:
- API Gateway (Spring Cloud Gateway)
- 5 Core Microservices
- React Frontend with role-based dashboards
- MongoDB Database
- Centralized Authentication & Authorization Service

---

## Module Distribution (5 Modules)

### Module 1: User Management & Authentication Service
**Developer 1 - Duration: 3-4 weeks**

#### Backend Requirements:
- **Multi-Role User Registration**
  - Vendor registration with business details verification
  - Public user registration with location preferences
  - Admin user creation (system-generated)
  - Email verification workflow for all user types
  
- **Role-Based Authentication**
  - JWT with role claims (VENDOR, ADMIN, PUBLIC_USER)
  - OAuth2 integration for public users
  - Role-specific access control
  - Session management with different token expiry by role
  
- **User Profile Management**
  - Vendor profiles: business info, contact details, verification status
  - Public user profiles: preferences, location, booking history summary
  - Admin profiles: permissions, activity logs

#### Frontend Requirements:
- **Multi-Role Registration/Login Forms**
  - Vendor registration with business details
  - Public user registration with location selection
  - Role-specific login flows
  
- **Dashboard Routing**
  - Vendor dashboard route protection
  - Admin dashboard route protection
  - Public user dashboard route protection

#### API Endpoints:
```
POST /api/auth/register/vendor
POST /api/auth/register/public
POST /api/auth/login
POST /api/auth/oauth/google (public users only)
GET /api/users/profile
PUT /api/users/profile
GET /api/users/vendor/verification-status
```

#### Database Collections:
- `users` (id, email, password, role, profile, location, verificationStatus, createdAt)
- `vendor_profiles` (userId, businessName, businessAddress, contactInfo, documents)
- `refresh_tokens` (token, userId, role, expiryDate)

---

### Module 2: Event Management Service (Vendor + Admin)
**Developer 2 - Duration: 3-4 weeks**

#### Backend Requirements:
- **Vendor Event Creation**
  - Event CRUD operations for vendors (own events only)
  - Event draft/published status management
  - Multiple ticket types with pricing tiers
  - Event scheduling with future dates
  - Image and document upload
  
- **Admin Event Approval Workflow**
  - Event approval/rejection system
  - Admin comments and feedback
  - Bulk approval operations
  - Event modification requests
  
- **Event Status Management**
  - Status: DRAFT → PENDING_APPROVAL → APPROVED/REJECTED → PUBLISHED
  - Auto-publish approved events on scheduled date
  - Event cancellation workflow

#### Frontend Requirements:
- **Vendor Dashboard**
  - Event creation form with rich editor
  - My Events list with status indicators
  - Event analytics and booking summaries
  - Event edit capabilities (pre-approval)
  
- **Admin Dashboard**
  - Pending events approval queue
  - Event review interface with approval/rejection
  - Vendor management section
  - System-wide event analytics

#### API Endpoints:
```
POST /api/events (vendor)
GET /api/events/vendor/{vendorId}
PUT /api/events/{id} (vendor - own events only)
GET /api/events/pending-approval (admin)
PUT /api/events/{id}/approve (admin)
PUT /api/events/{id}/reject (admin)
GET /api/events/analytics/vendor/{vendorId}
```

#### Database Collections:
- `events` (id, vendorId, title, description, venue, dateTime, status, approvalComments, ticketTypes, categories, images)
- `event_approvals` (eventId, adminId, status, comments, reviewDate)
- `event_analytics` (eventId, views, bookings, revenue, date)

---

### Module 3: Public Event Discovery & Search Service
**Developer 3 - Duration: 3-4 weeks**

#### Backend Requirements:
- **Location-Based Event Search**
  - Search events by user location (radius-based)
  - City/state/country filtering
  - Distance calculation and sorting
  
- **Advanced Search & Filtering**
  - Event type/category filtering
  - Date range filtering (upcoming events)
  - Price range filtering
  - Venue capacity filtering
  - Text search in event titles/descriptions
  
- **Event Recommendations**
  - Location-based recommendations
  - User preference-based suggestions
  - Popular events in area
  - Recently viewed events

#### Frontend Requirements:
- **Public Event Discovery Page**
  - Search bar with location and category filters
  - Interactive map view with event markers
  - List view with sorting options
  - Event cards with key information
  
- **Event Detail Page**
  - Comprehensive event information
  - Venue details and location map
  - Ticket types and pricing
  - Vendor information
  - Reviews and ratings section
  
- **Advanced Search Interface**
  - Multi-filter sidebar
  - Search results pagination
  - Save search preferences
  - Share event functionality

#### API Endpoints:
```
GET /api/events/public/search
GET /api/events/public/nearby
GET /api/events/public/{id}/details
GET /api/events/public/categories
GET /api/events/public/recommendations/{userId}
GET /api/events/public/upcoming
```

#### Database Collections:
- `public_events` (approved events with location indexing)
- `user_preferences` (userId, preferredCategories, location, searchHistory)
- `event_views` (userId, eventId, viewDate) - for analytics and recommendations

---

### Module 4: Booking & Reservation Service
**Developer 4 - Duration: 3-4 weeks**

#### Backend Requirements:
- **Seat Reservation System**
  - Real-time seat availability checking
  - Temporary seat holding (15-minute timer)
  - Concurrent booking prevention
  - Different seat categories and pricing
  
- **Booking Workflow**
  - Multi-step booking process
  - Guest checkout option for public users
  - Booking confirmation with QR codes
  - Booking modification and cancellation
  
- **Payment Integration**
  - Mock payment gateway integration
  - Multiple payment methods
  - Payment status tracking
  - Refund processing workflow
  
- **Pre-Event Booking Rules**
  - Booking cutoff times (e.g., 2 hours before event)
  - Early bird pricing
  - Group booking discounts

#### Frontend Requirements:
- **Seat Selection Interface**
  - Interactive seating map
  - Real-time availability updates
  - Seat category information
  - Price calculation
  
- **Booking Flow**
  - Guest/user booking options
  - Payment form with validation
  - Booking confirmation page
  - Email confirmation with tickets
  
- **User Booking Management**
  - Booking history and status
  - Ticket download/print options
  - Booking modification interface
  - Cancellation requests

#### API Endpoints:
```
GET /api/events/{id}/seats
POST /api/bookings/reserve
POST /api/bookings/confirm
GET /api/bookings/user/{userId}
PUT /api/bookings/{id}/cancel
POST /api/payments/process
GET /api/bookings/{id}/ticket
```

#### Database Collections:
- `bookings` (id, userId, eventId, seats, totalPrice, status, bookingDate, eventDate)
- `seat_reservations` (eventId, seatId, userId, expiryTime, reservationType)
- `payments` (id, bookingId, amount, status, paymentMethod, transactionId, refundStatus)
- `tickets` (bookingId, qrCode, downloadCount, printCount)

---

### Module 5: Analytics & Notification Service
**Developer 5 - Duration: 3-4 weeks**

#### Backend Requirements:
- **Vendor Analytics Dashboard**
  - Event performance metrics
  - Booking trends and analytics
  - Revenue reports by event
  - Audience demographics
  
- **Admin System Analytics**
  - Platform-wide statistics
  - Vendor performance metrics
  - User engagement analytics
  - Event approval turnaround times
  
- **Notification System**
  - Event approval/rejection notifications to vendors
  - Booking confirmations to users
  - Event reminders (24h, 2h before event)
  - Event updates and cancellations
  - Admin alerts for system events
  
- **Email Campaign Management**
  - Promotional emails for upcoming events
  - Newsletter subscriptions
  - Targeted marketing based on user preferences

#### Frontend Requirements:
- **Vendor Analytics Page**
  - Charts and graphs for event performance
  - Revenue tracking dashboard
  - Booking conversion metrics
  - Downloadable reports
  
- **Admin Analytics Dashboard**
  - System health monitoring
  - User growth metrics
  - Platform revenue tracking
  - Vendor performance rankings
  
- **Notification Center**
  - In-app notification panel
  - Notification preferences management
  - Email template management (admin)
  - Push notification settings

#### API Endpoints:
```
GET /api/analytics/vendor/{vendorId}
GET /api/analytics/admin/system
GET /api/notifications/user/{userId}
POST /api/notifications/send
PUT /api/notifications/preferences
GET /api/analytics/events/{eventId}/performance
```

#### Database Collections:
- `vendor_analytics` (vendorId, eventId, metrics, reportDate)
- `system_analytics` (date, userMetrics, eventMetrics, revenueMetrics)
- `notifications` (id, userId, type, message, status, createdAt, sentAt)
- `email_campaigns` (id, title, content, targetAudience, sentDate, metrics)

---

## Enhanced Technical Implementation

### Role-Based Access Control:
```java
@PreAuthorize("hasRole('VENDOR')")
@GetMapping("/vendor/events")
public ResponseEntity<List<Event>> getVendorEvents() { ... }

@PreAuthorize("hasRole('ADMIN')")
@PutMapping("/events/{id}/approve")
public ResponseEntity<Event> approveEvent(@PathVariable String id) { ... }

@PreAuthorize("hasRole('PUBLIC_USER') or hasRole('ADMIN')")
@GetMapping("/events/public/search")
public ResponseEntity<List<Event>> searchEvents() { ... }
```

### Event Approval Workflow:
```java
public enum EventStatus {
    DRAFT, PENDING_APPROVAL, APPROVED, REJECTED, 
    PUBLISHED, CANCELLED, COMPLETED
}
```

### Location-Based Search:
```java
// MongoDB geospatial query for nearby events
db.events.find({
    location: {
        $near: {
            $geometry: { type: "Point", coordinates: [lng, lat] },
            $maxDistance: radiusInMeters
        }
    },
    status: "APPROVED",
    eventDate: { $gte: new Date() }
})
```

### Concurrent Booking Prevention:
```java
@Transactional
public BookingResponse reserveSeat(String eventId, String seatId, String userId) {
    // Optimistic locking to prevent double booking
    // 15-minute reservation timer implementation
}
```

---

## Project Timeline (3-4 Weeks)

### Week 1: Foundation & Core Setup
- Multi-role authentication system
- Database design with role-based collections
- Basic vendor event creation
- Admin approval workflow setup

### Week 2: Core Business Logic
- Complete assigned module backend
- Event search and filtering
- Booking system implementation
- Role-based API security

### Week 3: Frontend Integration
- Role-specific React dashboards
- Event discovery and booking flow
- Admin approval interface
- Real-time updates and notifications

### Week 4: Integration & Deployment
- Inter-service communication
- Docker containerization
- End-to-end testing
- Performance optimization and demo preparation

---

## Enhanced Learning Outcomes

### Java Interview Topics Covered:
- **Spring Security**: Role-based authentication, method-level security
- **Microservices**: Service communication, data consistency
- **Concurrency**: Thread-safe booking system, optimistic locking
- **Design Patterns**: Factory (user creation), Strategy (payment methods), Observer (notifications)
- **Database**: MongoDB transactions, indexing, aggregation pipelines
- **System Design**: Multi-tenant architecture, workflow management

### Real-World Business Logic:
- Event approval workflows
- Location-based search algorithms
- Concurrent booking prevention
- Role-based access control
- Analytics and reporting systems

This enhanced project provides a comprehensive understanding of building enterprise-level applications with complex business rules, multiple user types, and real-world workflows that are commonly discussed in Java developer interviews.
