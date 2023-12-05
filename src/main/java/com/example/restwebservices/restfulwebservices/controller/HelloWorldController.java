package com.example.restwebservices.restfulwebservices.controller;

import com.example.restwebservices.restfulwebservices.entity.HelloWorld;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/test-api/")
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    @GetMapping(path = "hello-world")
    public HelloWorld getMessage() {
        return HelloWorld.builder().message("Hello Bean").build();
        //  return new HelloWorld("Hello Bean");
    }

    @GetMapping("test-api")
    public String getTestMessage() {
        return "TEst api is working fine";
    }

    @GetMapping("hello-world/name/{name}")
    public HelloWorld getName(@PathVariable("name") String name) {
        return new HelloWorld("Hello " + name);
    }

    @GetMapping("/message/internationalized")
    public String getMessageInternationalized(){

        Locale locale = LocaleContextHolder.getLocale();
        String message =  messageSource.getMessage("good.morning.message",
                null, "Default message given", locale);
        /*String message = HelloWorld.builder()
                .message("good morning")
                .build()
                .toString();*/
        return message;
    }


    @GetMapping(path = "/name", params = "version=1")
    public String getName(){
        return "Saif";
    }

    @GetMapping(path = "/name", params = "version=2")
    public String getFullName(){
        return "Saif Ali Shaikh";
    }

    @GetMapping( path = "/name", headers = "X-API-VERSION=1")
    public HelloWorld getMessageUsingHeaders(){
        return HelloWorld.builder().message("Message returned from version 1 of X-API")
                .build();
    }

    @GetMapping( path = "/name", headers = "X-API-VERSION=2")
    public HelloWorld getMessageUsingHeadersV2(){
        return HelloWorld.builder().message("Message returned from version 2 of X-API")
                .build();
    }

    @GetMapping( path = "/name/v1")
    public HelloWorld getMessageUsingURLV1(){
        return HelloWorld.builder()
                .message("Message returned from version 1 of URL")
                .build();
    }
    @GetMapping( path = "/name/v2")
    public HelloWorld getMessageUsingURLV2(){
        return HelloWorld.builder()
                .message("Message returned from version 2 of URL")
                .build();
    }

    @GetMapping( path = "/name", produces = "application/vnd.company.app-v1+json")
    public HelloWorld getMessageUsingContentV1(){
        return HelloWorld.builder()
                .message("Message returned from version 1 of content")
                .build();
    }

    @GetMapping( path = "/name", produces = "application/vnd.company.app-v2+json")
    public HelloWorld getMessageUsingContentV2(){
        return HelloWorld.builder()
                .message("Message returned from version 2 of content")
                .build();
    }
}
