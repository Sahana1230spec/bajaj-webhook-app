# Bajaj Finserv Health - Webhook SQL Solver

## Developer Information
- **Name:** Sahana R
- **Registration Number:** 1RF22CS094
- **Email:** sahanaraghu49@gmail.com

## Project Overview
This Spring Boot application automatically solves the Bajaj Finserv Health Java qualifier challenge. The application runs a webhook-based workflow that:

1. Generates a webhook URL and access token on startup
2. Determines the SQL question based on registration number (even/odd)
3. Submits the SQL solution via authenticated API call
4. Completes the task without manual intervention

## Technology Stack
- **Java:** 17.0.7
- **Spring Boot:** 3.1.0
- **Maven:** 3.8.x
- **Build Tool:** Maven Wrapper
- **Runtime:** Embedded Tomcat

## Task Requirements Met
- ✅ Sends POST request to generate webhook on startup
- ✅ No controller/endpoint triggers the flow (uses @EventListener)
- ✅ Determines question based on regNo last two digits (94 = even = Question 2)
- ✅ Uses RestTemplate for HTTP requests
- ✅ Implements JWT authorization with Bearer token
- ✅ Submits final SQL query to webhook URL

## SQL Problem Solved
**Question 2** (Even RegNo): Calculate the number of employees who are younger than each employee, grouped by their respective departments.

### Database Schema
```sql
-- DEPARTMENT table
DEPARTMENT_ID (PK) | DEPARTMENT_NAME

-- EMPLOYEE table  
EMP_ID (PK) | FIRST_NAME | LAST_NAME | DOB | GENDER | DEPARTMENT (FK)

-- PAYMENTS table
PAYMENT_ID (PK) | EMP_ID (FK) | AMOUNT | PAYMENT_TIME
```

### SQL Solution
```sql
SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, 
       COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT 
FROM EMPLOYEE e1 
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID 
LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB 
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME 
ORDER BY e1.EMP_ID DESC
```

## API Endpoints Used
1. **Webhook Generation:**
   - URL: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
   - Method: POST
   - Body: `{"name": "Sahana R", "regNo": "1RF22CS094", "email": "sahanaraghu49@gmail.com"}`

2. **Solution Submission:**
   - URL: `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
   - Method: POST
   - Headers: `Authorization: Bearer <accessToken>`
   - Body: `{"finalQuery": "SQL_QUERY_HERE"}`

## Project Structure
```
bajaj-webhook-app/
├── mvnw                          # Maven wrapper (Unix)
├── mvnw.cmd                      # Maven wrapper (Windows)
├── pom.xml                       # Maven configuration
├── README.md                     # Project documentation
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar     # Maven wrapper JAR
│       └── maven-wrapper.properties
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── bajaj/
│       │           └── webhook/
│       │               ├── BajajWebhookApp.java      # Main application
│       │               ├── WebhookResponse.java      # Response model
│       │               └── WebhookService.java       # Core logic
│       └── resources/                                # Configuration files
└── target/
    ├── classes/                  # Compiled classes
    ├── webhook-app-1.0.0.jar     # Executable JAR
    └── webhook-app-1.0.0.jar.original
```

## How to Build and Run

### Prerequisites
- Java 17 or higher
- Internet connection (for Maven dependencies)

### Build Commands
```bash
# Using Maven Wrapper (recommended)
./mvnw clean package              # Linux/Mac
.\mvnw.cmd clean package          # Windows

# Skip tests for faster build
./mvnw clean package -DskipTests
```

### Run Application
```bash
java -jar target/webhook-app-1.0.0.jar
```

### Expected Output
```
Starting Bajaj Process...
Webhook: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Solving Question 2 (Even regNo)
Submitted! Status: 200
DONE!
```

## Application Flow
1. **Startup:** Spring Boot application starts
2. **Auto-trigger:** `@EventListener(ApplicationReadyEvent.class)` fires
3. **Generate Webhook:** POST to `/generateWebhook/JAVA` with personal details
4. **Receive Response:** Get webhook URL and JWT access token
5. **Determine Question:** RegNo 1RF22CS094 → last digits 94 → even → Question 2
6. **Submit Solution:** POST SQL query to webhook URL with JWT auth
7. **Complete:** Application continues running (task completed)

## Key Implementation Details
- **No Controllers:** Uses event listener pattern instead of REST endpoints
- **Automatic Execution:** Runs immediately on application startup
- **JWT Authentication:** Properly implements Bearer token authorization
- **Error Handling:** Includes try-catch blocks for network operations
- **Spring Boot Best Practices:** Uses dependency injection and service layer

## Dependencies
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```
