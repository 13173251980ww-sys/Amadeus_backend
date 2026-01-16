package com.amadeus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/ai")
public class ChatController {

    private final ChatClient chatClient;

    @PostMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestBody String prompt){
        return chatClient
                .prompt()
                .user(prompt)
                .stream()
                .content();
    }
}
