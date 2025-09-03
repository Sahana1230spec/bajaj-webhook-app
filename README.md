\# Bajaj Finserv Health Challenge - Spring Boot Webhook Application



\## 🎯 Challenge Overview

This Spring Boot application was developed as part of the Bajaj Finserv Health Java challenge. As my registration number (1RF22CS094) is even, I implemented \*\*Question 2\*\* - the SQL database solution.



\## 🚀 Project Features



\### 1. REST API Endpoints

\- \*\*POST /bfhl\*\* - Processes input data and returns formatted response

\- \*\*GET /bfhl\*\* - Returns operation code for verification



\### 2. SQL Solution (Question 2)

\*\*Problem Statement:\*\* Count the number of employees younger than each employee in the same department, ordered by EMP\_ID in descending order.



\*\*SQL Query Implemented:\*\*

```sql

SELECT e1.EMP\_ID, e1.EMP\_NAME, e1.DEPARTMENT, e1.AGE,

&nbsp;      COUNT(e2.EMP\_ID) as YOUNGER\_EMPLOYEES\_COUNT

FROM EMPLOYEES e1

LEFT JOIN EMPLOYEES e2 ON e1.DEPARTMENT = e2.DEPARTMENT 

&nbsp;                      AND e2.AGE < e1.AGE

GROUP BY e1.EMP\_ID, e1.EMP\_NAME, e1.DEPARTMENT, e1.AGE

ORDER BY e1.EMP\_ID DESC;

```



\### 3. Webhook Integration

\- Automated webhook generation and submission

\- Error handling for external API calls

\- Structured response format with user details



\## 🛠️ Technical Stack

\- \*\*Java Version:\*\* 17.0.7

\- \*\*Spring Boot:\*\* 3.1.0

\- \*\*Maven:\*\* 3.8.x (via Maven Wrapper)

\- \*\*Build Tool:\*\* Maven

\- \*\*Server:\*\* Embedded Tomcat (Port 8080)



\## 📁 Project Structure

```

bajaj-webhook-app/

├── src/main/java/com/bajaj/webhook/

│   ├── BajajWebhookApp.java          # Main Spring Boot Application

│   ├── WebhookService.java           # Business Logic \& SQL Solution

│   └── WebhookResponse.java          # Response Model

├── target/

│   └── webhook-app-1.0.0.jar        # Compiled JAR file

├── pom.xml                           # Maven dependencies

└── README.md                         # This file

```



\## 🏃‍♀️ How to Run



\### Prerequisites

\- Java 17 or higher

\- Internet connection for Maven dependencies



\### Build \& Run Commands

```bash

\# Build the project

./mvnw clean package



\# Run the application

java -jar target/webhook-app-1.0.0.jar

```



\### API Testing

Once running on `http://localhost:8080`:



\*\*GET Request:\*\*

```bash

curl http://localhost:8080/bfhl

```



\*\*POST Request:\*\*

```bash

curl -X POST http://localhost:8080/bfhl \\

&nbsp; -H "Content-Type: application/json" \\

&nbsp; -d '{"data": \["A","C","z","c","i"]}'

```



\## 📊 SQL Solution Details



\### Database Schema

```sql

CREATE TABLE EMPLOYEES (

&nbsp;   EMP\_ID INT PRIMARY KEY,

&nbsp;   EMP\_NAME VARCHAR(100),

&nbsp;   DEPARTMENT VARCHAR(100),

&nbsp;   AGE INT

);

```



\### Solution Logic

1\. \*\*Self-Join:\*\* Join employees table with itself on department

2\. \*\*Age Comparison:\*\* Count employees with lower age in same department

3\. \*\*Grouping:\*\* Group by employee details to get individual counts

4\. \*\*Ordering:\*\* Sort by EMP\_ID in descending order



\### Expected Output Format

| EMP\_ID | EMP\_NAME | DEPARTMENT | AGE | YOUNGER\_EMPLOYEES\_COUNT |

|---------|----------|------------|-----|-------------------------|

| 103 | Alice | IT | 28 | 1 |

| 102 | Bob | IT | 25 | 0 |

| 101 | Carol | HR | 30 | 0 |



\## ✅ Build Status

\- \*\*Status:\*\* ✅ BUILD SUCCESS

\- \*\*Build Time:\*\* 3:21 minutes

\- \*\*JAR Location:\*\* `target/webhook-app-1.0.0.jar`

\- \*\*Application Startup:\*\* ✅ Successfully started on port 8080



