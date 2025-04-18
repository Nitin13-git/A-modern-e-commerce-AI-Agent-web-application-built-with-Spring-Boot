package com.Ecommerce.webApp.controller;

import com.Ecommerce.webApp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String handleChat(@RequestBody String userQuery) {
        return chatService.handleUserQuery(userQuery);
    }
} 