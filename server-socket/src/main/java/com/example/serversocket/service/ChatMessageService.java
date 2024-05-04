package com.example.serversocket.service;

import com.example.serversocket.model.ChatMessage;
import com.example.serversocket.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderNickName(), chatMessage.getRecipientNickName(), true); // You can create your own dedicated exception
        chatMessage.setChatId(chatId);
        chatMessage.setId(UUID.randomUUID().toString());
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        return repository.findChatMessageBySenderNickNameAndRecipientNickName(senderId,recipientId);
    }

    public List<ChatMessage> getAll() {
        return repository.findAll();
    }
}
