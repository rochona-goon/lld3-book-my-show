package com.scaler.bookMyshow.Services.Rating;

import com.scaler.bookMyshow.Exceptions.MovieNotFoundException;
import com.scaler.bookMyshow.Exceptions.UserNotFoundException;
import com.scaler.bookMyshow.Models.Movie;
import com.scaler.bookMyshow.Models.User;
import com.scaler.bookMyshow.Models.UserRating;
import com.scaler.bookMyshow.Repositories.MovieRepository;
import com.scaler.bookMyshow.Repositories.UserRatingRepository;
import com.scaler.bookMyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class RatingServiceImpl implements RatingService{
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final UserRatingRepository userRatingRepository;

    @Autowired
    public RatingServiceImpl(UserRepository userRepository, MovieRepository movieRepository, UserRatingRepository userRatingRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.userRatingRepository = userRatingRepository;
    }

    @Override
    public UserRating rateMovie(int userId, int movieId, int rating) {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating should be between 1 and 5");
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = optionalUser.get();

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = optionalMovie.get();

        // Check if user has already rated the movie
        UserRating userRating;
        Optional<UserRating> optionalUserRating = userRatingRepository.findByUserAndMovie(user, movie);
        if(optionalUserRating.isPresent()){
            userRating = optionalUserRating.get();
            userRating.setRating(rating);
        }else{
            userRating = new UserRating();
            userRating.setUser(user);
            userRating.setMovie(movie);
            userRating.setRating(rating);
        }

        return userRatingRepository.save(userRating);
    }


    @Override
    public double getMovieRating(int movieId) {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty()){
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = optionalMovie.get();

        List<UserRating> userRatingList = userRatingRepository.findByMovie(movie);

        if(userRatingList.isEmpty()){
            return 0.0;
        }

        OptionalDouble optOverallRating = userRatingList.stream()
                .mapToDouble(UserRating::getRating).average();


        return optOverallRating.orElse(0.0);

    }
}
