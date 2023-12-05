package com.example.restwebservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
//@JsonFilter("UserFilter")
//@JsonIgnoreProperties({"user_name", "birthDate"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonIgnore
    private int id;

    @NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
    @JsonProperty("user_name")
    private String name;

    @NotNull
//    @Past(message = "Birth date should be from past")
    private String birthDate;
}
