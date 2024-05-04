package com.example.serversocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Comparator;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_message")
public class ChatMessage implements Comparator<ChatMessage> {
    @Id
    private String id;
    private String chatId;
    private String senderNickName;
    private String recipientNickName;
    private String content;
    private Date timestamp;

    @Override
    public int compare(ChatMessage o1, ChatMessage o2) {

        if(o1.timestamp.after(o2.timestamp)) return 1;
        else if(o1.timestamp.before(o2.timestamp)) return -1;
        return 0;
    }
}
