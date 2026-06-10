package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Movie;
import com.scaler.bookMyshow.Models.User;
import com.scaler.bookMyshow.Models.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRatingRepository extends JpaRepository<UserRating, Integer> {
    Optional<UserRating> findByUserAndMovie(User user, Movie movie);

    List<UserRating> findByMovie(Movie movie);
}

