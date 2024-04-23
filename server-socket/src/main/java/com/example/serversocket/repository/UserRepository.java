package com.example.serversocket.repository;

import com.example.serversocket.model.Status;
import com.example.serversocket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {
    List<User> findAllByStatus(Status status);
    User findFirstByNickNameAndPassword(String nickname,String password);

}
