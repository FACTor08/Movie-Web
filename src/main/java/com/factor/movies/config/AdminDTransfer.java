package com.factor.movies.config;

import com.factor.movies.entity.Admin;
import com.factor.movies.entity.AdminDTO;
import com.factor.movies.entity.Roles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDTransfer {

    private final PasswordEncoder encoder;

    public AdminDTransfer(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Admin transferobj(AdminDTO admin){
        Admin data = new Admin();
            data.setEmail(admin.getEmail());
            data.setFirstname(admin.getFirstname());
            data.setPassword(encoder.encode(admin.getPassword()));
            data.setPhone(admin.getPhone());
            data.setRole(Roles.ROLE_ADMIN);
            data.setSurname(admin.getSurname());
            data.setUsername(admin.getUsername());
    return data;
    }
}
