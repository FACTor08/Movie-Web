package com.factor.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@lombok.Data
@Entity
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private Roles  role;

    private String email;

    private String firstname;
    private String password;

    @Size(min = 11, max = 11, message = "Phone-number shouldn't be more or less than 11 digits!!!")
    private String phone;

    private String lastname;
    private String username;
}
