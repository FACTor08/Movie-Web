package com.factor.movies.login;

import com.factor.movies.config.JwtUtil;
import com.factor.movies.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Login {
    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Users users = loginService.login(loginRequest);

        return jwtUtil.generateToken(users);
    }
}
