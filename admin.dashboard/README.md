# Movie-Dashboard-Application


A full-stack web application built with Spring Boot 3 (Java) for the backend and Angular 16+ for the frontend.
The app provides an Admin Dashboard and a User Dashboard with JWT-based authentication and OMDb API integration.

 Features
 Authentication & Authorization

User Registration & Login (JWT secured).

Role-based access (ADMIN, USER).

Secure API endpoints with Spring Security.

 Movies Management

Admin:

Add movies manually.

Delete movies.

Fetch movies from OMDb API.

User:

View movies.

Search movies.

 Tech Stack

Backend: Spring Boot 3.3.3, Spring Security, JWT, Spring Data JPA, H2 (in-memory DB).

Frontend: Angular 16+, TypeScript, Tailwind (optional).

API: OMDb API
 for fetching movie data.

 Backend Setup (Spring Boot 3.3.3)
 Clone the Repository
git clone https://github.com/your-username/movie-dashboard.git
cd movie-dashboard/backend

 Configure Application

Update application.properties or application.yml:

spring:
  datasource:
    url: jdbc:h2:mem:movies;DB_CLOSE_DELAY=-1;MODE=PostgreSQL
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

h2:
  console:
    enabled: true
    path: /h2-console

app:
  jwt:
    secret: change-this-super-secret-key-please
    expiration-ms: 86400000
  omdb:
    base-url: https://www.omdbapi.com/
    api-key: ${OMDB_API_KEY:demo}

 Run the Backend
mvn spring-boot:run


Backend will be available at:
 http://localhost:8080

 Frontend Setup (Angular 16+)
 Go to Frontend
cd ../frontend

 Install Dependencies
npm install

 Start Angular
ng serve --open


Frontend will run at:
 http://localhost:4200

 API Endpoints
Authentication

POST /api/auth/register → Register new user.

POST /api/auth/login → Login and get JWT.

Movies

GET /api/movies → Get all movies.

POST /api/movies → Add new movie (Admin only).

DELETE /api/movies/{id} → Delete movie (Admin only).

GET /api/movies/search?title=Inception → Search movies.

 Example Postman Requests
Register User
POST /api/auth/register
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}

Login
POST /api/auth/login
{
  "username": "admin",
  "password": "admin123"
}


 Response will include a JWT token to use in Authorization: Bearer <token> headers.

 Database

Using H2 In-Memory DB (auto resets on restart).

Access console at:
 http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:movies
 Users & Roles
Username	Password	Role
admin	admin123	ADMIN
user	user123	USER
 Future Improvements

Pagination for movies.

Rating system.

Batch add/remove movies.

Persistent DB (PostgreSQL/MySQL).

License

MIT License.
