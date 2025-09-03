# Bajaj Finserv Webhook Application

## Developer Details
- **Name:** Sahana R
- **Registration Number:** 1RF22CS094
- **Email:** sahanaraghu49@gmail.com

## Technology Stack
- Java 17.0.7
- Spring Boot 3.1.0
- Maven 3.8.x
- Embedded Tomcat (Port 8080)

## API Endpoints
- **GET /bfhl** - Returns operation code
- **POST /bfhl** - Processes input data and returns response

## SQL Solution (Question 2)
Count employees younger than each employee in the same department, ordered by EMP_ID DESC.

```sql
SELECT e1.EMP_ID, e1.EMP_NAME, e1.DEPARTMENT, e1.AGE,
       COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEES e1
LEFT JOIN EMPLOYEES e2 ON e1.DEPARTMENT = e2.DEPARTMENT 
                       AND e2.AGE < e1.AGE
GROUP BY e1.EMP_ID, e1.EMP_NAME, e1.DEPARTMENT, e1.AGE
ORDER BY e1.EMP_ID DESC;
```

## Project Structure
```
bajaj-webhook-app/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── .mvn/
│   └── wrapper/
│       ├── maven-wrapper.jar
│       └── maven-wrapper.properties
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── bajaj/
│       │           └── webhook/
│       │               ├── BajajWebhookApp.java
│       │               ├── WebhookResponse.java
│       │               └── WebhookService.java
│       └── resources/
└── target/
    ├── webhook-app-1.0.0.jar
    ├── webhook-app-1.0.0.jar.original
    └── classes/
```

## Build & Run
```bash
# Build
./mvnw clean package

# Run
java -jar target/webhook-app-1.0.0.jar
```

## Testing
```bash
# GET
curl http://localhost:8080/bfhl

# POST
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["A","C","z","c","i"]}'
```

