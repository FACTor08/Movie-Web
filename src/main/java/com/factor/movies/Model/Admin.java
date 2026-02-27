package com.factor.movies.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private Roles  role;

    @Email(message = "Enter a Valid email")
    private String email;

    private String firstname;
    private String password;

    @Size(min = 9, max = 11, message = "Phone-number must not preceed 9 digits or exceed 11!!!")
    private String phone;

    private String surname;
    private String username;
}
