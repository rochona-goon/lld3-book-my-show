package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.Genre;
import com.scaler.bookMyshow.Enums.Language;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String movieTitle;
    private int year;
    private Date releaseDate;
    private String director;

    @ElementCollection(targetClass = Genre.class)
    private List<Genre> genres;

    @ElementCollection(targetClass = Language.class)
    private List<Language> languages;

    private int rating;

    @OneToMany(mappedBy = "movie")
    private List<UserRating> userRatings;
    private double duration;

}
