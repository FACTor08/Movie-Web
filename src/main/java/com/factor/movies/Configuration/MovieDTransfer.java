package com.factor.movies.Configuration;

import com.factor.movies.Model.Movies;
import com.factor.movies.Model.MoviesDTO;
import org.springframework.stereotype.Component;

@Component
//public class scanner(){
//
//}
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
