package com.example.serversocket.controller;

import com.example.serversocket.model.User;
import com.example.serversocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/users/public")
    public User addUser(@Payload User user) {
        User user1 = userService.checkLogin(user);
        System.out.println("user: "+user);
        System.out.println("user1: "+user1);
        userService.login(user1);
        System.out.println("login success");
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/users/public")
    public User disconnectUser(@Payload User user) {
        User user1 = userService.checkLogin(user);
        System.out.println("user: "+user);
        System.out.println("user1: "+user1);
        userService.disconnect(user1);
        System.out.println("disconnect success");
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
