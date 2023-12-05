package com.example.restwebservices.restfulwebservices.controller;

import com.example.restwebservices.restfulwebservices.entity.User;
import com.example.restwebservices.restfulwebservices.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public EntityModel<User> getUser(@PathVariable("id") int id){
        User user = userService.getUser(id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
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

    @GetMapping("/filtered")
    public MappingJacksonValue getAllUsers(){
        List<User> users = userService.getAllUsers();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birthDate");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
