package com.factor.movies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MoviesDTO {

    private String bannerType;

    private String title;

    private Date year;


}
