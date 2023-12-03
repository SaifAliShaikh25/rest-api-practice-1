package com.example.restwebservices.restfulwebservices.controller;

import com.example.restwebservices.restfulwebservices.entity.HelloWorld;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-api/")
public class HelloWorldController {

    @GetMapping(path = "hello-world")
    public HelloWorld getMessage(){
        return HelloWorld.builder().message("Hello Bean").build();
      //  return new HelloWorld("Hello Bean");
    }

    @GetMapping("test-api")
    public String getTestMessage(){
        return "TEst api is working fine";
    }

    @GetMapping("hello-world/name/{name}")
    public HelloWorld getName(@PathVariable("name") String name){
        return new HelloWorld("Hello "+name);
    }
}
