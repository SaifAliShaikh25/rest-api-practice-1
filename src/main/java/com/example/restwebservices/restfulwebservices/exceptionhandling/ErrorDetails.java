package com.example.restwebservices.restfulwebservices.exceptionhandling;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String description;
}
