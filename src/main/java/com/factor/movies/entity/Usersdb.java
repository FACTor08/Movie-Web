package com.factor.movies.entity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface Usersdb extends JpaRepository<Users, Long>{
   Optional<Users> findByEmailIgnoreCase(String email);

    Optional<Users> findByUsername(String username);
}
