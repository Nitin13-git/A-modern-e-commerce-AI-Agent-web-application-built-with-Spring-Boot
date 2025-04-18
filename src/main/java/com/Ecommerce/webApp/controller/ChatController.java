package com.Ecommerce.webApp.controller;

import com.Ecommerce.webApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String handleGetChat(@RequestParam String query) {
        return chatService.handleUserQuery(query);
    }

    @PostMapping
    public String handlePostChat(@RequestBody String query) {
        return chatService.handleUserQuery(query);
    }

    @GetMapping("/stream")
    public Flux<String> streamGetChat(@RequestParam String query) {
        return chatService.streamUserQuery(query);
    }

    @PostMapping("/stream")
    public Flux<String> streamPostChat(@RequestBody String query) {
        return chatService.streamUserQuery(query);
    }
} 