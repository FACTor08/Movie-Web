package com.factor.movies.service;

import com.factor.movies.config.UserDTransfer;
import com.factor.movies.entity.Users;
import com.factor.movies.entity.UsersDTO;
import com.factor.movies.repository.Userdb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class UserLogic {
    @Autowired
    private Userdb repo;

    @Autowired
    private UserDTransfer userDT;

    @Autowired
    private PasswordEncoder encoder;


    public String registerUsers(UsersDTO data){

        if(repo.findByEmailIgnoreCase(data.getEmail()).isPresent()){
            return "Email already exists!!!";
        }

        Users store = userDT.transferobj(data);
       repo.save(store);
       return "User successfully registered";
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
    public Optional<Users> updateData(String user, UsersDTO key) {
        return repo.findByUsername(user).map(data -> {
            if (key.getUsername() != null) {
                data.setUsername(key.getUsername());
            }
            if (key.getEmail() != null) {
                data.setEmail(key.getEmail());
            }
            return repo.save(data);
        });
    }
    public void deleteAccount(String username, String rawPassword){
          Users user = repo.findByUsername(username)
                  .orElseThrow(()-> new UsernameNotFoundException("User not Found"));

          if(!encoder.matches(rawPassword,user.getPassword())){
              throw new RuntimeException("Wrong Password");
          }
              repo.delete(user);
    }
    }
