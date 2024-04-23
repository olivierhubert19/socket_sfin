package com.example.serversocket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
