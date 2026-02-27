package com.factor.movies.Repository;

import com.factor.movies.Model.Users;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface Userdb extends JpaRepository<Users, Long>{
   Optional<Users> findByEmailIgnoreCase(String email);

    Optional<Users> findByUsername(String username);



}
