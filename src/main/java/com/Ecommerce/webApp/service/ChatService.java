package com.Ecommerce.webApp.service;

import com.Ecommerce.webApp.model.Product;
import com.Ecommerce.webApp.model.Order;
import com.Ecommerce.webApp.prompt.EcommerceRouterPrompt;
import org.springframework.ai.model.ModelClient;
import org.springframework.ai.model.ModelResponse;
import org.springframework.ai.model.ModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final ModelClient modelClient;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public ChatService(ModelClient modelClient, ProductService productService, OrderService orderService) {
        this.modelClient = modelClient;
        this.productService = productService;
        this.orderService = orderService;
    }

    public String handleUserQuery(String userQuery) {
        // Create the system prompt with the router instructions
        String promptText = String.format(EcommerceRouterPrompt.DEFAULT_ROUTER_PROMPT, 
            EcommerceRouterPrompt.getAllToolDescriptions()) + "\n\nUser query: " + userQuery;
        
        // Get the AI's response
        ModelRequest request = ModelRequest.builder()
            .prompt(promptText)
            .build();
        
        ModelResponse response = modelClient.call(request);
        String aiResponse = response.getOutput();

        // Parse the AI's response and execute the appropriate action
        if (aiResponse.contains("getAllProducts")) {
            List<Product> products = productService.getAllProducts();
            return formatProductList(products);
        } else if (aiResponse.contains("searchProducts")) {
            // Extract search keyword from the query
            String keyword = extractKeyword(userQuery);
            List<Product> products = productService.getAllProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                                 product.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
            return formatProductList(products);
        } else if (aiResponse.contains("getProductById")) {
            // Extract product ID from the query
            String productId = extractProductId(userQuery);
            Product product = productService.getProductById(productId);
            return formatProductDetails(product);
        } else if (aiResponse.contains("createOrder")) {
            // Extract order details from the query
            Order order = extractOrderDetails(userQuery);
            Order createdOrder = orderService.createOrder(order);
            return formatOrderConfirmation(createdOrder);
        } else {
            return "I'm not sure how to handle that request. Could you please rephrase or provide more details?";
        }
    }

    private String formatProductList(List<Product> products) {
        if (products.isEmpty()) {
            return "No products found.";
        }
        StringBuilder response = new StringBuilder("Here are the available products:\n");
        for (Product product : products) {
            response.append(String.format("- %s (ID: %s, Price: $%.2f)\n", 
                product.getName(), product.getId(), product.getPrice()));
        }
        return response.toString();
    }

    private String formatProductDetails(Product product) {
        if (product == null) {
            return "Product not found.";
        }
        return String.format("Product Details:\nName: %s\nID: %s\nPrice: $%.2f\nDescription: %s",
            product.getName(), product.getId(), product.getPrice(), product.getDescription());
    }

    private String formatOrderConfirmation(Order order) {
        return String.format("Order created successfully!\nOrder ID: %s\nTotal Price: $%.2f",
            order.getId(), order.getTotalPrice());
    }

    private String extractKeyword(String query) {
        // Simple keyword extraction - in a real system, you'd want more sophisticated NLP
        return query.replaceAll("(?i)search|find|show|me|products?|for|about", "").trim();
    }

    private String extractProductId(String query) {
        // Simple ID extraction - in a real system, you'd want more sophisticated NLP
        return query.replaceAll("(?i)product|id|show|me|details|of|about", "").trim();
    }

    private Order extractOrderDetails(String query) {
        // Simple order details extraction - in a real system, you'd want more sophisticated NLP
        Order order = new Order();
        // Set default values or extract from query
        order.setQuantity(1); // Default quantity
        order.setCustomerName("Customer"); // Default name
        return order;
    }
} 