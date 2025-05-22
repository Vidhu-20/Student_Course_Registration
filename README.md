Student Course Registration Microservices Project 
SUMMARY
📌 Project Purpose & Functionality
The Student Course Registration System is designed to allow students to enroll in courses, manage their registration details, and track fee payments efficiently. It follows a microservices architecture, ensuring modularity, scalability, and independent service management.
🔧 Microservices & Their Roles
1️⃣ Student Service: Manages student details (studentId, firstName, lastName, email, mobile, age).
2️⃣ Course Service: Manages course details (courseId, courseName, duration, courseFee).
3️⃣ Course Registration Service: Handles student enrollments (registrationId, studentId, courseId, date_of_reg, feePaid, feesPending, status).
Each microservice runs independently, communicating via API calls using Feign Client.
🛠️ Tech Stack Used
✅ Spring Boot – Core framework for microservices.
✅ Spring Cloud Netflix Eureka Server – Enables service discovery for dynamic routing.
✅ Spring Cloud Gateway – Central API gateway for request handling.
✅ Spring Cloud OpenFeign – Handles inter-service communication.
✅ Spring Data JPA & MySQL – Database & ORM for persistence.
✅ Swagger (SpringDoc) – API documentation & testing.
🔗 How Everything Works Together
- A student registers for a course using the Course Registration Service.
- The Course Registration Service retrieves fee details from the Course Service via Feign Client.
- It automatically calculates pending fees and updates payment status (Fully Paid / Fee Pending).
- All services register with Eureka Server, allowing dynamic discovery.
- Requests are routed through the API Gateway, centralizing access.
- Swagger UI provides an interactive API testing interface.
