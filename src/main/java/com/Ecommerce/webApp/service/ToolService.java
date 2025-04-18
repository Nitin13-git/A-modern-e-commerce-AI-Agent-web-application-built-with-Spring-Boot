package com.Ecommerce.webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Ecommerce.webApp.model.Order;
import com.Ecommerce.webApp.model.Product;
import com.Ecommerce.webApp.repository.OrderRepository;
import com.Ecommerce.webApp.repository.ProductRepository;

import java.util.List;

@Service
public class ToolService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Order bookProduct(String productId, int quantity, String customerName) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        // Update product stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // Create and save order
        Order order = new Order(productId, quantity, customerName);
        order.setTotalPrice(product.getPrice() * quantity);
        return orderRepository.save(order);
    }
}

