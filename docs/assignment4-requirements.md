# Domexa Rentals – Observability Task Force

## Scenario

Domexa Rentals is scaling its property‑management platform and needs full observability across its microservices. Your team has been hired to implement **monitoring, metrics, and distributed tracing** for one core service.

You will build, instrument, monitor, visualize, trace, and present your findings.

## Learning Outcomes

By completing this assignment, you will be able to:

- Integrate **Spring Boot Actuator** for health and metrics
- Expose and customize **Micrometer metrics**
- Configure **Prometheus** to scrape application metrics
- Build **Grafana dashboards** for visualization
- Implement **Micrometer Tracing + Zipkin** for distributed tracing
- Analyze system behavior under load
- Present observability insights like a real engineering team

## Project Requirements

### 1. Microservice Setup

Create or reuse a Spring Boot microservice such as:

- Property Service
- Tenant Service
- Inspection Service

Your service must include:

- REST endpoints
- Basic business logic
- Optional: simulated latency or random failures

### 2. Spring Boot Actuator

You must:

- Enable Actuator
- Expose:
  - /actuator/health
  - /actuator/metrics
  - /actuator/prometheus
  - /actuator/info
- Add custom metadata in /info
- Create at least **one custom business metric** (e.g., domexa.property.created.count)

### 3. Prometheus Integration

You must:

- Configure Prometheus to scrape your service
- Validate collection of:
  - JVM metrics
  - HTTP metrics
  - Custom metrics

**Optional:** Add alerting or recording rules.

### 4. Grafana Dashboards

You must create dashboards for:

- JVM performance
- HTTP traffic (latency, throughput, errors)
- Custom business metrics
- A combined “Service Overview” dashboard

**Optional:** Add correlation panels (e.g., traffic vs latency).

### 5. Distributed Tracing with Zipkin

You must:

- Add Micrometer Tracing
- Configure Zipkin exporter
- Generate traces through endpoint calls
- Demonstrate:
  - Trace IDs
  - Spans
  - Latency breakdown

**Optional:** Add baggage fields (e.g., tenantId).

### 6. Load Testing (Optional but Recommended)

Use JMeter, k6, or Postman Runner to simulate:

- Traffic spikes
- Slow endpoints
- Errors

Show how these appear in:

- Prometheus
- Grafana
- Zipkin

## Final Presentation Requirements (10–12 minutes)

Your presentation must include:

### A. Architecture Overview

- Microservice diagram
- Monitoring stack diagram

### B. Live Demo

- Actuator endpoints
- Prometheus scraping
- Grafana dashboards
- Zipkin traces

### C. Observability Insights

- What metrics revealed
- How tracing helped diagnose issues
- What you would improve

### D. Team Reflection

- Roles
- Challenges
- Lessons learned

# Submission Package

Each group must submit:

- Source code (GitHub link)
- Prometheus config file
- Grafana dashboard JSON exports
- Zipkin screenshots
- Presentation slides (PDF)

# Assessment

## Grading Rubric (PDF‑Ready)

**Rubric: End‑to‑End Monitoring & Observability Project**

**Total: 100 points**

### 1. Microservice Implementation — 15 points

| **Criteria**                               | **Points** |
| ------------------------------------------ | ---------- |
| Functional Spring Boot microservice        | 5          |
| Clear business logic relevant to scenario  | 5          |
| Simulated latency/errors for observability | 5          |

### 2. Spring Boot Actuator & Metrics — 20 points

| **Criteria**                              | **Points** |
| ----------------------------------------- | ---------- |
| Actuator enabled and correctly configured | 5          |
| Required endpoints exposed                | 5          |
| Custom /info metadata                     | 5          |
| Custom Micrometer metric implemented      | 5          |

### 3. Prometheus Integration — 15 points

| **Criteria**                             | **Points** |
| ---------------------------------------- | ---------- |
| Prometheus scraping configured correctly | 5          |
| JVM + HTTP metrics collected             | 5          |
| Custom metric visible in Prometheus      | 5          |

### 4. Grafana Dashboards — 20 points

| **Criteria**                          | **Points** |
| ------------------------------------- | ---------- |
| JVM dashboard                         | 5          |
| HTTP traffic dashboard                | 5          |
| Custom metric visualization           | 5          |
| Combined “Service Overview” dashboard | 5          |

### 5. Distributed Tracing (Micrometer + Zipkin) — 20 points

| **Criteria**                    | **Points** |
| ------------------------------- | ---------- |
| Tracing configured correctly    | 5          |
| Trace IDs + spans visible       | 5          |
| Latency breakdown demonstrated  | 5          |
| Clear explanation of trace flow | 5          |

### 6. Final Presentation — 10 points

| **Criteria**             | **Points** |
| ------------------------ | ---------- |
| Architecture explanation | 3          |
| Live demo quality        | 3          |
| Insights & analysis      | 2          |
| Team reflection          | 2          |

### 7. Professionalism & Submission Quality — 5 points

| **Criteria**                    | **Points** |
| ------------------------------- | ---------- |
| Code organization & clarity     | 2          |
| Dashboard exports & screenshots | 2          |
| Overall polish                  | 1          |

## Assignment Due Date

See Class Website

## Late Submission Policy

| **Days Late** | **Penalty**              |
| ------------- | ------------------------ |
| 1 day late    | -20% cumulative penalty  |
| 2 days late   | -40% cumulative penalty  |
| 3 days late   | -100% cumulative penalty |

## Assignment Weight

Assignment Three is worth 20% of your final grade.

Please submit your assignment online under Assignments > Assignment Four.

**⚠️  Important**

Please ensure that any work you submit is your own, unique, and independent work. Work submitted that is found to be not your own, unique, and independent work will be subjected to a grade of 0 and considered to be academic misconduct.

# STUDENT STARTER TEMPLATE — Spring Boot Microservice (Monitoring Assignment)

**_Domexa Rentals — Property Service (Starter Code)_**

## 1. Project Structure

property-service/

├── src/

│   ├── main/

│   │   ├── java/com/domexa/property/

│   │   │   ├── PropertyServiceApplication.java

│   │   │   ├── controller/PropertyController.java

│   │   │   ├── service/PropertyService.java

│   │   │   ├── model/Property.java

│   │   │   └── metrics/PropertyMetrics.java

│   │   └── resources/

│   │       ├── application.yml

│   │       └── data.json (optional seed data)

├── pom.xml

└── README.md

## 2. pom.xml (Starter Dependencies)

<project xmlns="http://maven.apache.org/POM/4.0.0">

<modelVersion>4.0.0</modelVersion>

<groupId>com.domexa</groupId>

<artifactId>property-service</artifactId>

<version>1.0.0</version>

<dependencies>

<!-- Spring Boot Web -->

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-web</artifactId>

</dependency>

<!-- Actuator (students will expose endpoints) -->

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-actuator</artifactId>

</dependency>

<!-- Micrometer (metrics + tracing) -->

<dependency>

<groupId>io.micrometer</groupId>

<artifactId>micrometer-core</artifactId>

</dependency>

<!-- Micrometer Tracing (students will configure Zipkin) -->

<dependency>

<groupId>io.micrometer</groupId>

<artifactId>micrometer-tracing-bridge-brave</artifactId>

</dependency>

<!-- Zipkin Reporter -->

<dependency>

<groupId>io.zipkin.reporter2</groupId>

<artifactId>zipkin-reporter-brave</artifactId>

</dependency>

<!-- Lombok (optional but helpful) -->

<dependency>

<groupId>org.projectlombok</groupId>

<artifactId>lombok</artifactId>

<optional>true</optional>

</dependency>

<!-- Testing -->

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-test</artifactId>

<scope>test</scope>

</dependency>

</dependencies>

</project>

## 3. application.yml (Starter Config)

Students will later add Prometheus, Zipkin, and custom Actuator exposure.

server:

port: 8081

spring:

application:

name: property-service

management:

endpoints:

web:

exposure:

include: health,info,metrics,prometheus

endpoint:

health:

show-details: always

## 4. Main Application

package com.domexa.property;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class PropertyServiceApplication {

public static void main(String[] args) {

SpringApplication.run(PropertyServiceApplication.class, args);

}

}

## 5. Domain Model

package com.domexa.property.model;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor

public class Property {

private String id;

private String address;

private String owner;

private double rent;

}

## 6. Service Layer

package com.domexa.property.service;

import com.domexa.property.model.Property;

import org.springframework.stereotype.Service;

import java.util.\*;

@Service

public class PropertyService {

private final Map<String, Property> properties = new HashMap<>();

public PropertyService() {

// Seed data

properties.put("P1", new Property("P1", "12 King St", "Alice", 1800));

properties.put("P2", new Property("P2", "99 Queen St", "Bob", 2200));

}

public List<Property> getAll() {

return new ArrayList<>(properties.values());

}

public Property create(Property property) {

properties.put(property.getId(), property);

return property;

}

}

## 7. Custom Metrics (Students Will Expand This)

package com.domexa.property.metrics;

import io.micrometer.core.instrument.Counter;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.stereotype.Component;

@Component

public class PropertyMetrics {

private final Counter propertyCreatedCounter;

public PropertyMetrics(MeterRegistry registry) {

this.propertyCreatedCounter = Counter.builder("domexa.property.created.count")

.description("Number of properties created")

.register(registry);

}

public void incrementCreated() {

propertyCreatedCounter.increment();

}

}

## 8. Controller

package com.domexa.property.controller;

import com.domexa.property.metrics.PropertyMetrics;

import com.domexa.property.model.Property;

import com.domexa.property.service.PropertyService;

import org.springframework.web.bind.annotation.\*;

import java.util.List;

@RestController

@RequestMapping("/properties")

public class PropertyController {

private final PropertyService service;

private final PropertyMetrics metrics;

public PropertyController(PropertyService service, PropertyMetrics metrics) {

this.service = service;

this.metrics = metrics;

}

@GetMapping

public List<Property> getAll() {

return service.getAll();

}

@PostMapping

public Property create(@RequestBody Property property) {

metrics.incrementCreated();

return service.create(property);

}

}

## 9. README.md (Student Instructions)

# Domexa Property Service (Starter Template)

This starter project is used for the Monitoring & Observability assignment.

## Your Tasks

1. Enable and expose Actuator endpoints
2. Add custom info metadata
3. Configure Prometheus to scrape /actuator/prometheus
4. Build Grafana dashboards
5. Add Micrometer Tracing + Zipkin
6. Generate traces and analyze performance
7. Prepare your final presentation

## Run the service

mvn spring-boot:run

## Endpoints

GET  /properties

POST /properties

GET  /actuator/health

GET  /actuator/metrics

GET  /actuator/prometheus
