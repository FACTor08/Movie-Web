package com.factor.movies.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Users {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Roles role;

    @Email(message = "Input a valid Email Address")
    private String email;

    private String password;

    private String username;
}



