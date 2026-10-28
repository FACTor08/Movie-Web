package com.factor.movies.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientDTO {

    private String email;
    private String password;
    private String username;
}
