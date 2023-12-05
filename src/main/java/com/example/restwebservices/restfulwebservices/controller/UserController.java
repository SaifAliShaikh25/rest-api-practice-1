package com.example.restwebservices.restfulwebservices.controller;

import com.example.restwebservices.restfulwebservices.entity.User;
import com.example.restwebservices.restfulwebservices.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
       User savedUser = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id){
        return userService.getUser(id);
    }
    @GetMapping("/test")
    public String getMessage(){
        return "Rest api working fine";
    }

    @Hidden  //Will be used if Authentication is applied to hide this api in Swagger UI
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}
