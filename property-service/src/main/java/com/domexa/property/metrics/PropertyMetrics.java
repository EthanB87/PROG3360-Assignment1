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
