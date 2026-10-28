package com.factor.movies.movies;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MoviesLogic logic;

    @PostMapping(value = "/upload_movie", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieResponseDTO> uploadMovies(@ModelAttribute MoviesDTO data,
                                               @RequestPart("movie") MultipartFile movie,
                                               @RequestPart("poster") MultipartFile poster,
                                               @RequestPart("subtitle") MultipartFile subtitle
                                              ) throws IOException {

        MovieResponseDTO movies = logic.uploadMovies(data, movie, poster, subtitle);
        return ResponseEntity.ok(movies);

    }

    @GetMapping("/movies/search")
    public ResponseEntity<MovieResponseDTO> getByTitle(@RequestParam(value = "q") String title) {
       MovieResponseDTO movie = logic.findMovieByTitle(title);
       return ResponseEntity.ok(movie);
    }

}