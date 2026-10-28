package com.factor.movies.client;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private final PasswordEncoder encoder;

    public ClientMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    public Users requestMapper(ClientDTO usersDTO){
        Users users = new Users();

        users.setPassword(encoder.encode(usersDTO.getPassword()));
        users.setRole(Roles.ROLE_USER);
        users.setUsername(usersDTO.getUsername());
        users.setEmail(usersDTO.getEmail());

        if (usersDTO.getPassword() == null || usersDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (usersDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        else{
            users.setPassword(encoder.encode(usersDTO.getPassword()));
        }

        return users;
    }
    public ClientResponseDTO responseMapper(Users users){
       return ClientResponseDTO.builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .roles(users.getRole())
                .build();
    }
}
