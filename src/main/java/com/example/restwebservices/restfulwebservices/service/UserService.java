package com.example.restwebservices.restfulwebservices.service;

import com.example.restwebservices.restfulwebservices.entity.User;
import com.example.restwebservices.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.example.restwebservices.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
       return userRepository.save(user);
    }

    public User getUser(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return userRepository.findById(id).get();
        else
            throw new UserNotFoundException("id: "+id+" not found");
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
