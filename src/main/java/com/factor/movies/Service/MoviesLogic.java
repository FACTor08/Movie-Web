package com.factor.movies.Service;

import com.factor.movies.Configuration.MovieDTransfer;
import com.factor.movies.Model.Movies;
import com.factor.movies.Model.MoviesDTO;
import com.factor.movies.Repository.Moviesdb;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoviesLogic {

    @Autowired
    private Moviesdb repo;
    @Autowired
    private MovieDTransfer trnsf;

    public String uploadMovies(MoviesDTO data, MultipartFile movie, MultipartFile banner
            , MultipartFile subtitle) throws IOException {

        if (repo.findByTitleIgnoreCase(data.getTitle()).isPresent()) {
            return "Movie already exists";
        } else {

            byte[] image = banner.getBytes();
            byte[] sub = subtitle.getBytes();
            byte[] visuals = movie.getBytes(); // saving "movie file" to cloud storage to be implemented later on
                                               // uploading movies only as @lob can crash the jvm
            String imageType = banner.getContentType();

            Movies store = trnsf.transferObj(image, visuals, sub, data, imageType);
            repo.save(store);
            return "Movie: " + data.getTitle() + " has successfully been uploaded";
        }
    }


    public List<Movies> getAllMovies() {
        return repo.findAll();
    }


    public Optional<Movies> getByTitle(String title) {
        return repo.findByTitleIgnoreCase(title);
    }

    public Optional<Movies> updateData(String title, MoviesDTO details, MultipartFile file) {
        return repo.findByTitleIgnoreCase(title).map(data ->{
            if(details.getTitle() != null) data.setTitle(details.getTitle());
            if(details.getYear() != null) data.setYear(details.getYear());
            if(file != null && !file.isEmpty()) {
                try {
                    data.setMovie(file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("file upload failed", e);
                }
            }
            return repo.save(data);});
    }


    public void deleteData(String title){
       repo.deleteByTitleIgnoreCase(title);

    }
}