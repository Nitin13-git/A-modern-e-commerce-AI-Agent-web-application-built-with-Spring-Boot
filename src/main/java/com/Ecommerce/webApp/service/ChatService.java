package com.Ecommerce.webApp.service;

import com.Ecommerce.webApp.model.Product;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private final ProductService productService;

    @Autowired
    public ChatService(ChatClient.Builder chatClientBuilder, ProductService productService) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                    You are an AI assistant for an e-commerce store. Your role is to help users find and manage products.
                    
                    Available tools:
                    1. getAllProducts - Get a list of all available products
                    2. getProductById - Get details of a specific product by ID
                    3. searchProducts - Search products by keyword
                    
                    When a user asks about products:
                    1. If they want to see all products, use getAllProducts
                    2. If they want to find a specific product, use getProductById
                    3. If they want to search products, use searchProducts with their search term
                    
                    Always format your responses in a user-friendly way and include relevant product details.
                    """)
                .build();
        this.productService = productService;
    }

    public String handleUserQuery(String userQuery) {
        return chatClient.prompt()
                .user(userQuery)
                .call()
                .content();
    }

    public Flux<String> streamUserQuery(String userQuery) {
        return chatClient.prompt()
                .user(userQuery)
                .stream()
                .content();
    }
}