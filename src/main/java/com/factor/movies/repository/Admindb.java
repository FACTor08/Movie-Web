package com.factor.movies.repository;

import com.factor.movies.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Admindb extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmailIgnoreCase(String email);

    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByPassword(String password);
}