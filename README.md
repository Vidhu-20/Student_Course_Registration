# Student Course Registration System  

## 📌 Project Title Explanation  
The **Student Course Registration System** is designed to provide students with a seamless platform for enrolling in courses, managing registrations, and tracking fee payments. The system is built using a **microservices architecture**, ensuring modularity, scalability, and independent service management for optimized performance.  

## 📖 Project Overview  
This system allows students to:  
- **Browse and enroll** in available courses.  
- **Manage registration details**, including modifications and updates.  
- **Track fee payments**, with automated calculations and status updates.  

By leveraging **Spring Boot and Spring Cloud technologies**, the system ensures **dynamic service discovery, efficient API communication, and centralized request management** through an API Gateway.  

## 🚀 Features  
- **Student Enrollment & Course Registration** – Allows students to register and update enrollment.  
- **Automated Fee Processing** – Tracks and updates fee statuses dynamically.  
- **Microservices Architecture** – Enables independent and scalable service management.  
- **API Gateway Implementation** – Routes requests securely via **Spring Cloud Gateway**.  
- **Eureka-Based Service Discovery** – Manages dynamic service registration.  
- **Swagger-Integrated API Management** – Provides structured API documentation and testing.  

## 🛠 Tech Stack  
The project is developed using **modern technologies** to enhance reliability and efficiency:  
- **Java 17** – Primary programming language.  
- **Spring Boot** – Backend framework for microservices.  
- **Spring Cloud Gateway** – Centralized API Gateway.  
- **Spring Cloud Eureka** – Service discovery for scalability.  
- **Feign Client** – Simplified inter-service communication.  
- **MySQL** – Relational database management system.  
- **Swagger** – API documentation tool.  
- **Postman** – API testing and debugging.  

## ⚙️ Installation & Setup  
Follow these steps to **configure and run** the project:  

### **Step 1: Clone the Repository**  
```sh
git clone https://github.com/Vidhu-20/Student_Course_Registration.git
cd Student_Course_Registration
```
### **Step 2: Start Eureka Server**
```sh
The Eureka Server enables dynamic service discovery, ensuring microservices can communicate efficiently.
```
Navigate to the Eureka Server directory:
- cd eureka-server

Build and run the Eureka Server:
- mvn clean install
- mvn spring-boot:run

Verify the Eureka Dashboard is running by visiting:
- http://localhost:8761

# **Step 3: Start Microservices**  

Each microservice runs independently, ensuring modularity.  

## **Student Service**
```sh
cd student-service  
mvn clean install  
mvn spring-boot:run  
```
## **Course Service**
```sh
cd course-service
- mvn clean install
- mvn spring-boot:run
```
## **Registration Service**
```sh
cd registration-service
- mvn clean install
- mvn spring-boot:run
```
Confirm services are registered with Eureka Server by checking http://localhost:8761.

## **Step 4: Start API Gateway**  
The API Gateway routes requests to appropriate services.  

```sh
cd api-gateway  
mvn clean install  
mvn spring-boot:run  
```

Once running, test service routing via Gateway.

### **Step 5: Verify API Endpoints**
```sh
Use Postman or Curl to test API requests through the gateway:
curl -X GET http://localhost:9091/student/getall
curl -X GET http://localhost:9091/course/all
curl -X POST http://localhost:9091/register/create
```

### **📖 API Documentation**
```sh
Access Swagger UI for structured API reference:
http://localhost:9091/swagger-ui.html
```
