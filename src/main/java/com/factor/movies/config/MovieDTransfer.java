package com.factor.movies.config;

import com.factor.movies.entity.Movies;
import com.factor.movies.entity.MoviesDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieDTransfer {
    public Movies transferObj(byte[] image, byte[] movie, byte[] subtitle, MoviesDTO data,  String banner){
        Movies transfer = new Movies();

        transfer.setBannerType(banner);
        transfer.setMovie(movie);
        transfer.setMovieBanner(image);
        transfer.setSubtitle(subtitle);
        transfer.setTitle(data.getTitle());
        transfer.setYear(data.getYear());

        return transfer;
    }
}
