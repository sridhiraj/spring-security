package com.example.service;

import com.example.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> userList = new ArrayList<User>();

    public UserService(){
        userList.add(new User(UUID.randomUUID().toString(), "Tom","tom@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(), "Dick","dick@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(), "Harry","harry@gmail.com"));
    }

    public List<User> getUserList(){
        return userList;
    }

}
