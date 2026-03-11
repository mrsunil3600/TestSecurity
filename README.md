# 🔐 TestSecurity - JWT Authentication API

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=for-the-badge&logo=springboot)
![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green?style=for-the-badge&logo=mongodb)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue?style=for-the-badge&logo=jsonwebtokens)
![Maven](https://img.shields.io/badge/Maven-Build-success?style=for-the-badge&logo=apache-maven)

> **A production-ready Spring Boot REST API with JWT-based security and MongoDB integration for secure user authentication and management**

[Features](#-features) • [Installation](#-installation) • [Configuration](#-configuration) • [API Endpoints](#-api-endpoints) • [Project Structure](#-project-structure)

</div>

---

## 📋 Overview

**TestSecurity** is a robust RESTful API built with Spring Boot that demonstrates enterprise-grade security practices. It implements JWT (JSON Web Token) authentication for secure user login and session management, with MongoDB as the persistent data store. Perfect for learning security best practices or as a foundation for your authentication system.

### 🎯 Use Cases
- Secure user registration and login systems
- JWT-based API authentication
- User management platforms
- Microservices authentication layer
- Learning Spring Security with JWT

---

## ✨ Features

<table>
<tr>
<td>

### 🔑 Security Features
- ✅ **JWT Token Authentication** - Stateless session management
- ✅ **Spring Security Integration** - Industry-standard security framework
- ✅ **BCrypt Password Encryption** - Secure password hashing
- ✅ **CSRF Protection Disabled** - Configured for stateless API
- ✅ **Custom Security Filters** - JWT validation at request level
- ✅ **Role-Based Access Control** - Authorization ready

</td>
<td>

### 🚀 API Features
- ✅ **User Registration** - Create new user accounts
- ✅ **User Login** - Secure authentication with JWT tokens
- ✅ **User Retrieval** - Fetch user information
- ✅ **Token Validation** - Automatic JWT verification
- ✅ **RESTful Endpoints** - Clean API design
- ✅ **MongoDB Integration** - Flexible data storage

</td>
</tr>
</table>

---

## 🛠️ Tech Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| **Framework** | Spring Boot | 4.0.3 |
| **Language** | Java | 21 |
| **Database** | MongoDB | Latest |
| **Authentication** | JWT (JJWT) | 0.11.5 |
| **Security** | Spring Security | Latest |
| **Build Tool** | Maven | Latest |
| **ORM** | Spring Data MongoDB | Latest |
| **Annotations** | Lombok | Latest |

---

## 📦 Installation

### Prerequisites
Before you begin, ensure you have installed:
- ☕ **Java JDK 21** or higher - [Download](https://www.oracle.com/java/technologies/downloads/)
- 📦 **Maven 3.6+** - [Download](https://maven.apache.org/download.cgi)
- 🍃 **MongoDB** - [Download](https://www.mongodb.com/try/download/community)
- 🔧 **Git** - [Download](https://git-scm.com/)

### Clone & Setup

```bash
# Clone the repository
git clone https://github.com/yourusername/TestSecurity.git
cd TestSecurity

# Build the project with Maven
mvn clean install

# Run the application
mvn spring-boot:run
```

✅ The application will start on `http://localhost:8080`

---

## ⚙️ Configuration

### 📝 Application Properties
Update `src/main/resources/application.properties` with your configuration:

```properties
# Application name
spring.application.name=TestSecurity

# MongoDB Configuration
spring.data.mongodb.host=localhost        # MongoDB host
spring.data.mongodb.port=27017           # MongoDB port
spring.data.mongodb.database=Play        # Database name

# JWT Secret Key (Base64 encoded)
jwt.secret.key=V2hhdEV2ZXJZb3VEb1JlbWVtYmVyVG9LZWVwSXRTZWNyZXRTYWZlQW5kTG9uZ0Vub3VnaF9TdW5pbF9KV1RfSFM1MTJfS2V5XzIwMjY=
```

### 🔑 JWT Configuration Details

| Property | Description | Value |
|----------|-------------|-------|
| **Secret Key** | Base64 encoded secret for token signing | 256-bit key |
| **Token Expiration** | Token validity period | 1 hour (3600000ms) |
| **Algorithm** | Signing algorithm used | HMAC-SHA-512 |
| **Claims** | Custom claims in token | Username, User ID, Issue Time |

### 🔓 Public Endpoints
The following endpoints are accessible **without authentication**:
```
├── POST   /user/newuser/**      → Register new user
├── POST   /user/login/**        → Login and get JWT token
└── GET    /user/getone/**       → Get user information
```

### 🔒 Protected Endpoints
The following endpoints require valid JWT token:
```
└── GET    /user/getalluser      → Get all users (authenticated)
```

---

## 🌐 API Endpoints Documentation

### 1️⃣ Register New User
Create a new user account with username and password.

**Endpoint:** `POST /user/newuser`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "message": "User created successfully"
}
```

**Status Codes:**
- ✅ `200 OK` - User created successfully
- ❌ `400 Bad Request` - Invalid input data
- ❌ `409 Conflict` - User already exists

---

### 2️⃣ User Login
Authenticate user and receive JWT token.

**Endpoint:** `POST /user/login`

**Request Body:**
```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZSIsIklkIjoxLCJpYXQiOjE3MDAwMDAwMDB9.XXXX...",
  "message": "Login successful"
}
```

**Status Codes:**
- ✅ `200 OK` - Login successful, token provided
- ❌ `401 Unauthorized` - Invalid credentials
- ❌ `404 Not Found` - User not found

---

### 3️⃣ Get User Information
Retrieve specific user details by username.

**Endpoint:** `GET /user/getone/{username}`

**Path Parameters:**
- `username` (string) - The username to retrieve

**Response:**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Status Codes:**
- ✅ `200 OK` - User found
- ❌ `404 Not Found` - User not found

---

### 4️⃣ Get All Users (Protected)
Retrieve all users in the system. **Requires authentication.**

**Endpoint:** `GET /user/getalluser`

**Headers:**
```
Authorization: Bearer <your_jwt_token>
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "john_doe"
  },
  {
    "id": 2,
    "username": "jane_smith"
  }
]
```

**Status Codes:**
- ✅ `200 OK` - Users retrieved successfully
- ❌ `401 Unauthorized` - Missing or invalid JWT token
- ❌ `403 Forbidden` - Insufficient permissions

---

## 🏗️ Project Structure

```
TestSecurity/
│
├── 📄 pom.xml                          # Maven configuration & dependencies
├── 📄 mvnw & mvnw.cmd                  # Maven wrapper scripts
├── 📄 HELP.md                          # Help documentation
├── 📄 README.md                        # This file
│
└── src/
    ├── main/
    │   ├── java/SercurityTest/TestSecurity/
    │   │   ├── 🚀 TestSecurityApplication.java      # Main application entry point
    │   │   │
    │   │   ├── 🔐 configuration/
    │   │   │   ├── SecurityConfig.java              # Spring Security configuration
    │   │   │   └── JwtSecurityFilter.java           # Custom JWT validation filter
    │   │   │
    │   │   ├── 🌐 controllers/
    │   │   │   └── UserController.java              # REST API endpoints
    │   │   │
    │   │   ├── 🛣️ jwtservice/
    │   │   │   └── AuthJwtUtil.java                 # JWT token creation & validation
    │   │   │
    │   │   ├── 💼 service/
    │   │   │   └── UserService.java                 # Business logic for users
    │   │   │
    │   │   ├── 📊 entity/
    │   │   │   └── UserEntity.java                  # User data model (MongoDB)
    │   │   │
    │   │   ├── 💾 dao/
    │   │   │   ├── LoginRequestDao.java             # Login request DTO
    │   │   │   ├── UserRequestDao.java              # User creation request DTO
    │   │   │   └── UserResponseDao.java             # User response DTO
    │   │   │
    │   │   ├── 🔑 userdetailsimple/
    │   │   │   └── UserDetailsImpl.java              # Spring UserDetails implementation
    │   │   │
    │   │   └── 🗂️ userrepoimpl/
    │   │       └── UserRepoImpl.java                 # User repository implementation
    │   │
    │   └── resources/
    │       ├── ⚙️ application.properties             # Application configuration
    │       ├── static/                              # Static files (CSS, JS, etc.)
    │       └── templates/                           # HTML templates
    │
    └── test/
        └── java/SercurityTest/TestSecurity/
            └── TestSecurityApplicationTests.java    # Unit & integration tests
```

### 📚 Component Descriptions

| Component | Purpose | Key Responsibility |
|-----------|---------|-------------------|
| **TestSecurityApplication** | Entry Point | Starts the Spring Boot application |
| **SecurityConfig** | Configuration | Defines security rules, password encoding, filter chain |
| **JwtSecurityFilter** | Middleware | Intercepts requests and validates JWT tokens |
| **UserController** | API Routes | Exposes REST endpoints for user operations |
| **UserService** | Business Logic | Handles user registration, login, retrieval |
| **AuthJwtUtil** | Token Management | Creates and extracts JWT tokens |
| **UserEntity** | Data Model | MongoDB document representing user |
| **UserDetailsImpl** | Spring Security | Bridges user entity with Spring Security |
| **UserRepoImpl** | Data Access | Database operations for users |

---

## 🔄 Authentication Flow

```
┌─────────────────────────────────────────────────────────────┐
│                    JWT AUTHENTICATION FLOW                   │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  1️⃣  USER REGISTRATION                                       │
│      POST /user/newuser                                      │
│      └─→ Password encrypted with BCrypt                      │
│      └─→ User stored in MongoDB                              │
│                                                              │
│  2️⃣  USER LOGIN                                              │
│      POST /user/login                                        │
│      └─→ Credentials verified                                │
│      └─→ JWT Token generated (expires in 1 hour)             │
│      └─→ Token returned to client                            │
│                                                              │
│  3️⃣  PROTECTED REQUEST                                       │
│      GET /user/getalluser                                    │
│      Header: Authorization: Bearer <JWT_TOKEN>               │
│      └─→ JwtSecurityFilter intercepts request                │
│      └─→ Token validated using secret key                    │
│      └─→ User claims extracted                               │
│      └─→ Request processed or rejected                       │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 🧪 Testing the API

### Using cURL

```bash
# 1. Register a new user
curl -X POST http://localhost:8080/user/newuser \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123"}'

# 2. Login to get JWT token
curl -X POST http://localhost:8080/user/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"test123"}'

# 3. Get user info (public endpoint)
curl -X GET http://localhost:8080/user/getone/testuser

# 4. Get all users (requires token)
curl -X GET http://localhost:8080/user/getalluser \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### Using Postman

1. **Create New User**
   - Method: `POST`
   - URL: `http://localhost:8080/user/newuser`
   - Body (JSON): `{"username":"myuser","password":"mypass123"}`

2. **Login**
   - Method: `POST`
   - URL: `http://localhost:8080/user/login`
   - Body (JSON): `{"username":"myuser","password":"mypass123"}`
   - **Copy the returned token**

3. **Get All Users**
   - Method: `GET`
   - URL: `http://localhost:8080/user/getalluser`
   - Headers: `Authorization: Bearer <PASTE_TOKEN_HERE>`

---

## 🚀 Running the Application

### Option 1: Using Maven
```bash
# Navigate to project directory
cd TestSecurity

# Build and run
mvn clean install
mvn spring-boot:run
```

### Option 2: Using IDE
1. Open project in IntelliJ IDEA or Eclipse
2. Right-click on `TestSecurityApplication.java`
3. Select "Run 'TestSecurityApplication.main()'"

### Option 3: Running JAR
```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/TestSecurity-0.0.1-SNAPSHOT.jar
```

**✅ Success indicator:** You should see:
```
Started TestSecurityApplication in X.XXX seconds
```

---

## 🔍 Troubleshooting

### Common Issues & Solutions

| Issue | Cause | Solution |
|-------|-------|----------|
| **Connection refused on port 8080** | Port already in use | Kill process: `kill -9 :8080` or change port in application.properties |
| **MongoDB connection failed** | MongoDB not running | Start MongoDB service: `mongod` |
| **401 Unauthorized** | Invalid/missing JWT token | Ensure token is in Authorization header: `Bearer <token>` |
| **404 User not found** | User doesn't exist | Register new user first via `/user/newuser` |
| **Password mismatch on login** | Wrong password entered | Verify credentials and try again |
| **JWT token expired** | Token older than 1 hour | Request new token via login endpoint |

---

## 🛡️ Security Best Practices

### ✅ What This Project Does Right

- 🔐 **BCrypt Hashing** - Passwords never stored in plain text
- 🔑 **JWT Tokens** - Stateless authentication without sessions
- 🚫 **CSRF Protection** - Disabled for stateless API (best practice)
- 🔒 **Filter Chain** - All requests validated at security filter level
- 📝 **Configurable Secrets** - JWT secret externalized in properties

### 🚨 Production Recommendations

```
1. ✅ Use HTTPS/TLS in production (load balancer level)
2. ✅ Store JWT secret in environment variables, not code
3. ✅ Implement token refresh mechanism
4. ✅ Add rate limiting to prevent brute force attacks
5. ✅ Use stronger JWT secret key (32+ characters)
6. ✅ Implement logging and audit trails
7. ✅ Add CORS configuration if serving frontend separately
8. ✅ Use API Keys for additional service-to-service auth
9. ✅ Enable MongoDB authentication credentials
10. ✅ Monitor token expiration and implement refresh tokens
```

---

## 📝 MongoDB Schema

### User Collection: `MyUserData`

```json
{
  "_id": ObjectId("..."),
  "id": 1,
  "username": "john_doe",
  "password": "$2a$10$...",  // BCrypt hashed
  "enabled": true
}
```

**Indexes to create (optional but recommended):**
```javascript
db.MyUserData.createIndex({ "username": 1 }, { unique: true })
```

---

## 🔄 Workflow Example

### Complete User Journey

```
1. USER SIGNS UP
   Request:  POST /user/newuser
   Body:     { "username": "alice", "password": "secret123" }
   Response: "User created successfully"
   
2. USER LOGS IN
   Request:  POST /user/login
   Body:     { "username": "alice", "password": "secret123" }
   Response: { "token": "eyJhbGc...", "message": "Login successful" }
   
3. USER ACCESSES PROTECTED RESOURCE
   Request:  GET /user/getalluser
   Headers:  Authorization: Bearer eyJhbGc...
   Response: [{ "id": 1, "username": "alice" }, ...]
   
4. TOKEN EXPIRES (after 1 hour)
   Request:  GET /user/getalluser
   Headers:  Authorization: Bearer eyJhbGc... (old token)
   Response: 401 Unauthorized - "Invalid token"
   
5. USER LOGS IN AGAIN
   Request:  POST /user/login
   Body:     { "username": "alice", "password": "secret123" }
   Response: { "token": "newToken...", "message": "Login successful" }
```

---

## 📊 Database Interaction Flow

```
Request → Spring Controller
    ↓
UserService (Business Logic)
    ↓
UserRepository
    ↓
MongoDB (MyUserData collection)
    ↓
Result → Response
```

---

## 🎓 Learning Resources

- 📚 [Spring Boot Official Guide](https://spring.io/projects/spring-boot)
- 🔐 [Spring Security Documentation](https://spring.io/projects/spring-security)
- 🔑 [JWT.io - JSON Web Tokens](https://jwt.io)
- 🍃 [MongoDB Documentation](https://docs.mongodb.com/)
- 💡 [Spring Data MongoDB Guide](https://spring.io/projects/spring-data-mongodb)

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**Your Name/Organization**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com

---

## 🤝 Contributing

Contributions are welcome! Please help improve this project.

### How to Contribute

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Contribution Guidelines
- Follow Java naming conventions
- Write meaningful commit messages
- Add comments for complex logic
- Test your changes thoroughly
- Update README if adding new features

---

## 📞 Support & Issues

Found a bug or have questions? 

- 🐛 [Report a Bug](https://github.com/yourusername/TestSecurity/issues)
- 💬 [Start a Discussion](https://github.com/yourusername/TestSecurity/discussions)
- 📧 Email: support@example.com

---

## 🌟 Show Your Support

If you found this project helpful, please give it a ⭐ star! It means a lot!

```
If this helped you understand JWT/Spring Security:
→ Star this repo ⭐
→ Share with your network 🚀
→ Contribute improvements 💪
```

---

<div align="center">

### Made with ❤️ for the developer community

**Happy Coding! 🚀**

[↑ Back to top](#-testsecurity---jwt-authentication-api)

</div>
