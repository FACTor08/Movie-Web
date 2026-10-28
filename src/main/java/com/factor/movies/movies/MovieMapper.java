package com.factor.movies.movies;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MovieMapper {
    public Movies transferObj(
            MoviesDTO dto,
            Map<String, Object> video,
            Map<String, Object> image,
            Map<String, Object> subtitle){

        Movies transfer = new Movies();

        transfer.setTitle(dto.getTitle());
        transfer.setYear(dto.getYear());

        if (video != null) {
            transfer.setMovieUrl((String) video.get("secure_url"));
            transfer.setMoviePublicId((String) video.get("public_id"));
            if (video.get("duration") != null) {
            transfer.setDuration(((Number) video.get("duration")).doubleValue());
            }
            if(image != null) {
                transfer.setMovieImageUrl((String) image.get("secure_url"));
                transfer.setImagePublicId((String) image.get("public_id"));
            }
            if(subtitle != null) {
                transfer.setSubtitleUrl((String) subtitle.get("secure_url"));
                transfer.setSubtitlePublicId((String) subtitle.get("public_id"));
            }
        }

        return transfer;
    }
    public MovieResponseDTO responseDTO(Movies movie){
     return MovieResponseDTO.builder()
               .id(movie.getId())
               .title(movie.getTitle())
               .duration(movie.getDuration())
               .movieUrl(movie.getMovieUrl())
               .movieImageUrl(movie.getMovieImageUrl())
               .subtitleUrl(movie.getSubtitleUrl())
               .description(movie.getDescription())
               .year(movie.getYear())
               .build();

    }
}
