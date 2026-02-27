package com.factor.movies.Repository;

import com.factor.movies.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Admindb extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmailIgnoreCase(String email);

    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByPassword(String password);
}