package com.factor.movies.client;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.Usersdb;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientLogic {

    private final Usersdb repo;
    private final ClientMapper dataTransfer;
    private final PasswordEncoder encoder;


    public ClientResponseDTO registerUsers(ClientDTO data){

        if(repo.findByEmailIgnoreCase(data.getEmail()).isPresent()){
            throw new IllegalArgumentException("email already exists!!!");
        }

        Users client = dataTransfer.requestMapper(data);
        Users saved = repo.save(client);
        return dataTransfer.responseMapper(saved);
    }


    public ClientResponseDTO updateData(String user, ClientDTO key) {
     return repo.findByUsername(user).map(data ->{
        if (key.getUsername() != null) {
            data.setUsername(key.getUsername());
        }
        if (key.getEmail() != null) {
            data.setEmail(key.getEmail());
        }
            return repo.save(data);

        })
             .map(dataTransfer::responseMapper)
             .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }

    public void deleteAccount(String username, String rawPassword){
          Users user = repo.findByUsername(username)
                  .orElseThrow(()-> new UsernameNotFoundException("user not Found"));

          if(!encoder.matches(rawPassword,user.getPassword())){
              throw new BadCredentialsException("wrong Password");
          }
              repo.delete(user);
    }
    }
