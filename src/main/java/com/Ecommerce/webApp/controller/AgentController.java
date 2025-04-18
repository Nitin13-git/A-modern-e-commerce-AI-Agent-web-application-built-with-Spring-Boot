package com.Ecommerce.webApp.controller;

import com.Ecommerce.webApp.dto.BookingRequest;
import com.Ecommerce.webApp.model.Order;
import com.Ecommerce.webApp.model.Product;
import com.Ecommerce.webApp.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    private ToolService toolService;

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        logger.info("Searching for products with keyword: {}", keyword);
        List<Product> products = toolService.searchProducts(keyword);
        if (products.isEmpty()) {
            logger.info("No products found for keyword: {}", keyword);
            return ResponseEntity.noContent().build();
        }
        logger.info("Found {} products for keyword: {}", products.size(), keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(toolService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(toolService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(toolService.createProduct(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestBody Product product) {
        return ResponseEntity.ok(toolService.updateProduct(id, product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        toolService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/book")
    public ResponseEntity<Order> bookProduct(@RequestBody BookingRequest req) {
        logger.info("Received booking request: {}", req);
        
        if (req.getProductKeyword() == null || req.getProductKeyword().trim().isEmpty()) {
            logger.error("Invalid booking request: product keyword is empty");
            return ResponseEntity.badRequest().build();
        }
        
        if (req.getQuantity() <= 0) {
            logger.error("Invalid booking request: quantity must be positive");
            return ResponseEntity.badRequest().build();
        }
        
        if (req.getCustomerName() == null || req.getCustomerName().trim().isEmpty()) {
            logger.error("Invalid booking request: customer name is empty");
            return ResponseEntity.badRequest().build();
        }

        List<Product> matches = toolService.searchProducts(req.getProductKeyword());
        if (matches.isEmpty()) {
            logger.error("No products found for keyword: {}", req.getProductKeyword());
            return ResponseEntity.notFound().build();
        }
        
        Product chosen = matches.get(0);
        logger.info("Selected product for booking: {}", chosen);
        
        try {
            Order order = toolService.bookProduct(
                    chosen.getId(),
                    req.getQuantity(),
                    req.getCustomerName()
            );
            logger.info("Successfully created order: {}", order);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            logger.error("Error creating order: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}

