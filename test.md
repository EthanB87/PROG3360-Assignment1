🛠️ Improvements & Fixes

- Dependency Correction: Fixed the invalid Spring Boot version (4.0.4 -> 3.4.3) and added missing dependencies for
  automatic HTTP tracing (micrometer-observation and spring-boot-starter-aop).
- Configuration Update: Aligned application.yaml with Spring Boot 3 standards, ensuring traces are sent to Zipkin and
  metrics have the correct global tags.
- Simulated Failures: Added a new /properties/error endpoint that randomly fails, allowing you to observe error
  spikes in Prometheus and traces of failed requests in Zipkin.
- Business Logic: Enhanced the create method to prevent duplicate IDs, adding meaningful business logic for
  monitoring.
- Grafana Auto-Provisioning: Created a configuration to automatically set up the Prometheus datasource and the Domexa
  Property Service Overview dashboard upon startup.

🚀 How to see things
To apply these changes and see your metrics/traces, please follow these steps:

1.  Restart the Observability Stack:

1 docker-compose down
2 docker-compose up -d 2. Restart the Property Service:
Navigate to the property-service directory and run:
1 ./mvnw.cmd spring-boot:run 3. Generate Traffic:
Run these commands to populate the tools with data:
_ Normal: curl.exe http://localhost:8081/properties
_ Slow: curl.exe http://localhost:8081/properties/slow
_ Error: curl.exe http://localhost:8081/properties/error
_ Create (Custom Metric): Invoke-RestMethod -Uri http://localhost:8081/properties -Method Post -Body
'{"id":"P10", "address":"New St", "owner":"Dev", "rent":500}' -ContentType "application/json"

📊 Verification URLs

- Prometheus: http://localhost:9090 (http://localhost:9090) (Search for domexa_property_created_count_total)
- Grafana: http://localhost:3000 (http://localhost:3000) (Dashboard is pre-loaded; no manual setup needed)
- Zipkin: http://localhost:9411 (http://localhost:9411) (Click "Run Query" to see traces for property-service)
