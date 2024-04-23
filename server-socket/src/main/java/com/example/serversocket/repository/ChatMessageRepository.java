package com.example.serversocket.repository;

import com.example.serversocket.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findChatMessageBySenderIdAndRecipientId(String senderId,String recipientId);

}
