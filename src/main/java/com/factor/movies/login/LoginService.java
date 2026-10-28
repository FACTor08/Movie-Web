package com.factor.movies.login;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.Usersdb;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final Usersdb repo;
    private final PasswordEncoder encoder;

    public Users login(LoginRequest request) {
        Users user = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Wrong Username/Password"));

        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong Username/Password");
        }
        return user;
    }
}
