package com.factor.movies.repository;

import com.factor.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Moviesdb extends JpaRepository<Movies, Long> {

    Optional<Movies> findByTitleIgnoreCase(String title);
    void deleteByTitleIgnoreCase(String data);
}
