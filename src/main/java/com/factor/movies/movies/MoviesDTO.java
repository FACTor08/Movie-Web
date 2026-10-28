package com.factor.movies.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MoviesDTO {
    private String Description;
    private String title;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate year;
}
