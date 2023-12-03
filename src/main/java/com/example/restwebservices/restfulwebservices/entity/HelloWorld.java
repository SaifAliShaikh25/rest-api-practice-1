package com.example.restwebservices.restfulwebservices.entity;

import jakarta.persistence.Entity;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HelloWorld {
    private String message;
}
