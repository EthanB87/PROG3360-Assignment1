package org.example.productservice.Controller;

import org.example.productservice.Model.Product;
import org.example.productservice.Repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository _productRepository;

    public ProductController(ProductRepository productRepository) {
        _productRepository = productRepository;
    }

    @GetMapping()
    private List<Product> GetProducts() {
        return _productRepository.findAll();
    }

    @GetMapping("/{id}")
    private Product GetProductById(@PathVariable Long id) {
        return _productRepository.findById(id).orElseThrow();
    }

    @PostMapping()
    private Product AddProduct(@RequestBody Product product) {
        return _productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    private void DeleteProduct(@PathVariable Long id) {
        _productRepository.deleteById(id);
    }
}