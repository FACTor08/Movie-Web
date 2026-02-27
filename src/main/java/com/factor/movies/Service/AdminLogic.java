package com.factor.movies.Service;

import com.factor.movies.Configuration.AdminDTransfer;
import com.factor.movies.Model.Admin;
import com.factor.movies.Model.AdminDTO;
import com.factor.movies.Repository.Admindb;
import com.factor.movies.Repository.Userdb;
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
public class AdminLogic {

    @Autowired
    private Admindb repo;
    @Autowired
    private Userdb userRepo;
    @Autowired
    private AdminDTransfer trnsf;

    public String registerAdmin(AdminDTO data){

        if (repo.findByEmailIgnoreCase(data.getEmail()).isPresent()) {
            return "Email already exists";
        }

        Admin store = trnsf.transferobj(data);
        repo.save(store);
        return "New Admin Successfully Registered";
// added for development purpose

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public Optional<Admin> updateData(String user, AdminDTO key) {
        return repo.findByUsername(user).map(data -> {
            if (key.getUsername() != null) {
                data.setUsername(key.getUsername());
            }
            if (key.getPhone() != null ){
                data.setPhone(key.getPhone());
            }
            return repo.save(data);
        });

    }
}
