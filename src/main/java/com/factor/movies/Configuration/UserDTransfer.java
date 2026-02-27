package com.factor.movies.Configuration;

import com.factor.movies.Model.Roles;
import com.factor.movies.Model.Users;
import com.factor.movies.Model.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
