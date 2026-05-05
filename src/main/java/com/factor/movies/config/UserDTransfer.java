package com.factor.movies.config;

import com.factor.movies.entity.Roles;
import com.factor.movies.entity.Users;
import com.factor.movies.entity.UsersDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDTransfer {

    private final PasswordEncoder encoder;

    public UserDTransfer(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    public Users transferobj(UsersDTO usersDTO){
        Users users = new Users();

        users.setPassword(encoder.encode(usersDTO.getPassword()));
        users.setRole(Roles.ROLE_USER);
        users.setUsername(usersDTO.getUsername());
        users.setEmail(usersDTO.getEmail());
        return users;
    }
}
