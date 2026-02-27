package com.factor.movies.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UsersDTO {

    private String email;
    private String password;
    private String username;
}
