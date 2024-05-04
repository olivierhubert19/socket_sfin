package com.example.serversocket.controller;

import com.example.serversocket.model.ChatMessage;
import com.example.serversocket.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/performance")
@RequiredArgsConstructor
public class PerformanceController {
    static int check = 0;
    private final ChatMessageService chatMessageService;
    @GetMapping("/test_chat_message")
    public ResponseEntity<?> performance(){
        System.out.println(check++);
        List<ChatMessage> list = chatMessageService.getAll();
        return ResponseEntity.ok(list);
    }
}
