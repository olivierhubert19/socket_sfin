package com.example.serversocket.controller;

import com.example.serversocket.model.ChatMessage;
import com.example.serversocket.model.ChatNotification;
import com.example.serversocket.model.User;
import com.example.serversocket.service.ChatMessageService;
import com.example.serversocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageService chatMessageService;
    @MessageMapping("/chat")
    @SendTo("/users/public")
    public ChatNotification processMessage(@Payload ChatMessage chatMessage) {
        System.out.println("Process Chat:");
        System.out.println(chatMessage);
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        return new ChatNotification(
                savedMsg.getId(),
                savedMsg.getSenderNickName(),
                savedMsg.getRecipientNickName(),
                savedMsg.getContent()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId,
                                                 @PathVariable String recipientId) {
        List<ChatMessage> list1 = chatMessageService.findChatMessages(senderId, recipientId);
        List<ChatMessage> listResult = chatMessageService.findChatMessages(recipientId,senderId);
        listResult.addAll(list1);
        listResult.sort(new ChatMessage());
        return ResponseEntity.ok(listResult);
    }
}
