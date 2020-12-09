package com.example.javarestapi.service;

import java.util.List;
import java.util.Optional;

import com.example.javarestapi.model.User;
import com.example.javarestapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserFromId(int id){
        return userRepository.getUserFromId(id);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public Optional<User> save(User user){
        if(isValidToSave(user)){
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    private Boolean isValidToSave(User user){
        return true;
    }
}
