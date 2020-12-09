package com.example.javarestapi.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.javarestapi.model.User;
import com.example.javarestapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserFromId(@PathVariable int id){
        Optional<User> aux = service.getUserFromId(id);
        if(aux.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(aux.get());
    }
    
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user, HttpServletRequest request, UriComponentsBuilder builder){
        Optional<User> aux = service.save(user);
        if(aux.isEmpty())
            return ResponseEntity.badRequest().build();
        UriComponents uriComponents = builder.path(request.getRequestURI()+"/"+aux.get().getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

}
