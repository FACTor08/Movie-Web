package com.factor.movies.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Moviesdb extends JpaRepository<Movies, Long> {

    Optional<Movies> findByTitleContainingIgnoreCase(String title);
}
