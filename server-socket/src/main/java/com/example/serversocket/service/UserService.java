package com.example.serversocket.service;


import com.example.serversocket.model.Status;
import com.example.serversocket.model.User;
import com.example.serversocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    public User checkLogin(User user){
        return repository.findFirstByNickNameAndPassword(user.getNickName(), user.getPassword());
    }


    public void login(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getId()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public User findById(String id){
        return repository.findById(id).get();
    }
    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
