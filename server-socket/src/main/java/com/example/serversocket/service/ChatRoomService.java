package com.example.serversocket.service;

import com.example.serversocket.model.ChatRoom;
import com.example.serversocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public String getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findBySenderNickNameAndRecipientNickName(senderId, recipientId);

        if (chatRoomOptional.isPresent()) {
            // Nếu phòng chat tồn tại, trả về chatId của phòng đó
            return chatRoomOptional.map(ChatRoom::getChatId).orElse(null);
        } else {
            // Nếu phòng chat không tồn tại và được phép tạo mới
            if (createNewRoomIfNotExists) {
                // Tạo mới chatId và trả về
                return createChatId(senderId, recipientId);
            } else {
                // Nếu không được phép tạo mới, trả về null
                return null;
            }
        }
    }



    private String createChatId(String senderNickName, String recipientNickName) {
        var chatId = String.format("%s_%s", senderNickName, recipientNickName);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .id(UUID.randomUUID().toString())
                .chatId(chatId)
                .senderNickName(senderNickName)
                .recipientNickName(recipientNickName)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .id(UUID.randomUUID().toString())
                .chatId(chatId)
                .senderNickName(recipientNickName)
                .recipientNickName(senderNickName)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
