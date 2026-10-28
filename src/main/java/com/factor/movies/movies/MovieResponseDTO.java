package com.factor.movies.movies;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovieResponseDTO {

    private long id;
    private String title;
    private String movieUrl;
    private String subtitleUrl;
    private String movieImageUrl;
    private Double duration;
    private String description;
    private LocalDate year;

}
