package com.domexa.property.service;

import com.domexa.property.model.Property;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (properties.containsKey(property.getId())) {
            throw new IllegalArgumentException("Property with ID " + property.getId() + " already exists.");
        }
        properties.put(property.getId(), property);
        return property;
    }
}
