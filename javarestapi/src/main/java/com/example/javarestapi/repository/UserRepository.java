package com.example.javarestapi.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.javarestapi.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {
    private List<User> users;
    private int nextid;
    
    @PostConstruct
    public void init(){
        User utest1 = new User();
        utest1.setId(1);
        utest1.setName("Test 1");
        utest1.setBirthdate(LocalDate.now());
        utest1.setEmail("test1@test.com");
        utest1.setPremium(true);

        User utest2 = new User();
        utest2.setId(2);
        utest2.setName("Test 2");
        utest2.setBirthdate(LocalDate.now());
        utest2.setEmail("test2@test.com");
        utest2.setPremium(false);

        users = new ArrayList<User>();
        users.add(utest1);
        users.add(utest2);
        nextid = 3;
    }

    public User save(User user){
        user.setId(nextid);
        users.add(user);
        nextid++;
        return user; 
    }

    public Optional<User> getUserFromId(int id){
        for(User aux : users){
            if(aux.getId() == id){
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public List<User> getAllUsers(){
        return users;
    }
}
