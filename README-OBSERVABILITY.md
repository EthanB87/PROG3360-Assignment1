# Domexa Rentals - Observability Implementation

This project implements full observability for the `property-service` using Spring Boot Actuator, Micrometer, Prometheus, Grafana, and Zipkin.

## 1. Prerequisites

- Docker and Docker Compose
- Java 17+
- Maven (or use `./mvnw` provided in the service directory)

## 2. Running the Observability Stack

Start Prometheus, Grafana, and Zipkin:

```bash
docker-compose -f docker-compose.yml up -d
```

## 3. Running the Property Service

Navigate to the `property-service` directory and run:

```bash
./mvnw.cmd spring-boot:run
```

The service will be available at `http://localhost:8081`.

## 4. Observability Endpoints

- **Health:** [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)
- **Info:** [http://localhost:8081/actuator/info](http://localhost:8081/actuator/info)
- **Metrics:** [http://localhost:8081/actuator/metrics](http://localhost:8081/actuator/metrics)
- **Prometheus:** [http://localhost:8081/actuator/prometheus](http://localhost:8081/actuator/prometheus)

## 5. Tools

- **Prometheus:** [http://localhost:9090](http://localhost:9090) (Scrapes metrics from the service)
- **Grafana:** [http://localhost:3000](http://localhost:3000) (Default login: `admin`/`admin`)
  - _Import the dashboard from `grafana-dashboards/property-service-dashboard.json`_
- **Zipkin:** [http://localhost:9411](http://localhost:9411) (Distributed Tracing UI)

## 6. Custom Metrics

We have implemented a custom business metric: `domexa.property.created.count`.
To increment this metric, create a new property:

```bash
curl -X POST http://localhost:8081/properties \
-H "Content-Type: application/json" \
-d '{"id": "P3", "address": "123 Main St", "owner": "John Doe", "rent": 2500}'
```

## 7. Distributed Tracing

Traces are automatically generated for every HTTP request.
To see a trace with latency:

1. Call the slow endpoint: `curl http://localhost:8081/properties/slow`
2. Open Zipkin at `http://localhost:9411`
3. Click "Run Query" to see the trace for `property-service`.

## 8. Observability Insights

- **Metrics:** Revealed JVM memory usage patterns and request throughput. The custom metric `domexa.property.created.count` allows tracking business growth directly.
- **Tracing:** The `/properties/slow` endpoint demonstrates how Zipkin can identify bottlenecks (simulated 1s delay) in the call chain.
- **Improvements:** In a production environment, we would add alerting rules to Prometheus for high error rates or latency spikes.
