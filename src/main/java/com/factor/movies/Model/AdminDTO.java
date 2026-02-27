package com.factor.movies.Model;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminDTO {

    private String email;
    private String firstname;
    private String password;
    private String phone;
    private String surname;
    private String username;
}
