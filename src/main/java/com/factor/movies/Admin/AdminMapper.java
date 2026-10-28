package com.factor.movies.Admin;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    private final PasswordEncoder encoder;

    public AdminMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Users transferobj(AdminDTO admin){
        Users data = new Users();
            data.setEmail(admin.getEmail());
            data.setFirstname(admin.getFirstname());
                        data.setPhone(admin.getPhone());
            data.setRole(Roles.ROLE_ADMIN);
            data.setLastname(admin.getLastname());
            data.setUsername(admin.getUsername());

        if (admin.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
            if (admin.getPassword().length() < 8) {
                throw new IllegalArgumentException("Password must be at least 8 characters");
            }
            else{
                data.setPassword(encoder.encode(admin.getPassword()));
            }

    return data;
    }
    public AdminResponseDTO transferdto(Users data){
    return    AdminResponseDTO.builder()
                .firstname(data.getFirstname())
                .lastname(data.getLastname())
                .username(data.getUsername())
                .email(data.getEmail())
                .phone(data.getPhone())
                .role(data.getRole())
                .build();
    }
}
