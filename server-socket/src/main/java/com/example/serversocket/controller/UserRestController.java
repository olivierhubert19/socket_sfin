package com.example.serversocket.controller;

import com.example.serversocket.model.User;
import com.example.serversocket.service.JwtService;
import com.example.serversocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestBody User user){
        System.out.println(user);
        User userCheck = userService.checkLogin(user);
        System.out.println(userCheck);
        if (userCheck==null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Can't find user");
        else{
            return ResponseEntity.ok(jwtService.generateToken(user));
        }
    }

}
