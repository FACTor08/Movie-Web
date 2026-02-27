package com.factor.movies.Model;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MoviesDTO {

    private String bannerType;

    private String title;

    private String year;


}
