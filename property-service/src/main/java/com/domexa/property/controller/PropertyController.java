package com.domexa.property.controller;

import com.domexa.property.metrics.PropertyMetrics;
import com.domexa.property.model.Property;
import com.domexa.property.service.PropertyService;
import org.springframework.web.bind.annotation.*;

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

    // Simulated latency or random failure
    @GetMapping("/slow")
    public List<Property> getSlow() throws InterruptedException {
        Thread.sleep(1000); // Simulate delay
        return service.getAll();
    }

    @GetMapping("/error")
    public List<Property> getError() {
        if (new java.util.Random().nextBoolean()) {
            throw new RuntimeException("Simulated random failure");
        }
        return service.getAll();
    }

    @PostMapping
    public Property create(@RequestBody Property property) {
        metrics.incrementCreated();
        return service.create(property);
    }
}
