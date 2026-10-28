package com.factor.movies.Admin;

import com.factor.movies.entity.Users;
import com.factor.movies.entity.Usersdb;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Transactional
public class AdminLogic {

    private final Usersdb repo;
    private final AdminMapper dataTransfer;

    public AdminResponseDTO registerAdmin(AdminDTO data){

        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email already exists");
        }

        Users store = dataTransfer.transferobj(data);
        Users saved =  repo.save(store);
            return dataTransfer.transferdto(saved);
    }

    public AdminResponseDTO updateData(String user, AdminDTO key) {
        return repo.findByUsername(user)
                .map(data -> {
                    if (key.getUsername() != null && !key.getUsername().isBlank()) {
                        data.setUsername(key.getUsername());
                    }
                    if (key.getPhone() != null ){
                        data.setPhone(key.getPhone());
                    }
                    return repo.save(data);
                })
                .map(dataTransfer::transferdto)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

    }
}
