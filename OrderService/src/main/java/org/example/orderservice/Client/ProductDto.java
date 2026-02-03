package org.example.orderservice.Client;

public record ProductDto(
        Long id,
        String name,
        double price,
        int quantity
) {}