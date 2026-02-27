package com.factor.movies.Controller;

import com.factor.movies.Model.Movies;
import com.factor.movies.Model.MoviesDTO;
import com.factor.movies.Service.MoviesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class Main {

    @Autowired
    private MoviesLogic logic;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadMovies(@ModelAttribute MoviesDTO data,
                                               @RequestPart("movie") MultipartFile movie,
                                               @RequestPart("subtitle") MultipartFile subtitle,
                                               @RequestPart("poster") MultipartFile poster) throws IOException {

        String msg = logic.uploadMovies(data, movie, subtitle, poster);
        return ResponseEntity.ok(msg);

    }

    @GetMapping("/movies")
    public List<Movies> getAllMovies() {
        return logic.getAllMovies();
    }

    @GetMapping("/movies/search/{title}")
    public ResponseEntity<Movies> getByTitle(@PathVariable String title) {
        return logic.getByTitle(title).stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PatchMapping("/patch/{detail}")
    public ResponseEntity<Movies> patchData(@PathVariable String detail,
                                            @RequestPart(value = "metadata", required = false) MoviesDTO patch,
                                            @RequestPart(value = "file", required = false) MultipartFile file){
        Optional<Movies> updatedData = logic.updateData(detail, patch, file);
        return updatedData.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{movies}")
    public ResponseEntity<String> deleteMovies(@PathVariable String title){
        logic.deleteData(title);
        return ResponseEntity.ok("The movie '" + title + "' has been deleted successfully");
    }
}