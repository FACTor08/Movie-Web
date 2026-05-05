package com.factor.movies.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] movie;

    @Lob
    private byte[] movieBanner;

    @Lob
    private byte[] subtitle;

    private String bannerType;

    private String title;

    @Temporal(TemporalType.DATE)
    private Date year;
}
