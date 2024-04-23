package com.example.serversocket.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    private String id;
    private String nickName;
    private String fullName;
    private String password;
    private Status status;

    public User(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }

    public User(String nickName, String password, Status status) {
        this.nickName = nickName;
        this.password = password;
        this.status = status;
    }
}
