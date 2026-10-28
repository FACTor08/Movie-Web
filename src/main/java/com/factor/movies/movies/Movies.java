package com.factor.movies.movies;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 500)
    private String movieUrl;
    @Column(length = 500)
    private String movieImageUrl;
    @Column(length = 500)
    private String subtitleUrl;
    @Column(nullable = false)
    private String title;
    private String Description;
    private String imagePublicId;
    private String subtitlePublicId;
    private String moviePublicId;
    private Double duration;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate year;
}
