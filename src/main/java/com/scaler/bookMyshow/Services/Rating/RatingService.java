package com.scaler.bookMyshow.Services.Rating;

import com.scaler.bookMyshow.Models.UserRating;

public interface RatingService {

    UserRating rateMovie(int userId, int movieId, int rating);

    double getMovieRating(int movieId);
}
