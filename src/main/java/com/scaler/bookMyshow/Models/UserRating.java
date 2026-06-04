package com.scaler.bookMyshow.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserRating extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    private int rating;
}
