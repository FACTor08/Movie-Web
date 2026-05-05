package com.factor.movies.controller;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.UsersDTO;
import com.factor.movies.service.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRegistration{
    @Autowired
    private UserLogic logic;



    @PostMapping("/register/user")
    public ResponseEntity<String> addUsers(@RequestBody UsersDTO user){
        String msg = logic.registerUsers(user);
        return ResponseEntity.ok(msg);
    }
    @PatchMapping("/register/user/update")
    public ResponseEntity<Users> updateData(Authentication auth,@RequestBody UsersDTO user){
        return logic.updateData(auth.getName(), user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody UsersDTO user, Authentication auth){
        logic.deleteAccount(auth.getName(), user.getUsername());
        return ResponseEntity.ok("Account deleted successfully");
    } 
}
