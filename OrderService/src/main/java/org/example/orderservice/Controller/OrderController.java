package org.example.orderservice.Controller;

import org.example.orderservice.Client.ProductClient;
import org.example.orderservice.Client.ProductDto;
import org.example.orderservice.Model.Order;
import org.example.orderservice.Repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository _orderRepository;
    private final ProductClient _productClient;

    public OrderController(OrderRepository orderRepository, ProductClient productClient) {
        _orderRepository = orderRepository;
        _productClient = productClient;
    }

    @GetMapping()
    private List<Order> GetOrders() {
        return _orderRepository.findAll();
    }

    @GetMapping("/{id}")
    private Order GetOrderById(@PathVariable Long id) {
        return _orderRepository.findById(id).orElseThrow();
    }

    @PostMapping()
    private Order CreateOrder(@RequestBody Order order) {
        ProductDto product = _productClient.GetProductById(order.getProductId());

        if (product.quantity() < order.getQuantity()) {
            throw new RuntimeException("Insufficient product quantity");
        }

        order.setTotalPrice(product.price() * order.getQuantity());
        order.setStatus("ORDER CREATED");

        return _orderRepository.save(order);
    }
}