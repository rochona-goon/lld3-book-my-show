package com.scaler.bookMyshow.Controllers;

import com.scaler.bookMyshow.DTOs.Rating.FetchRatingResponseDto;
import com.scaler.bookMyshow.DTOs.Rating.RatingRequestDto;
import com.scaler.bookMyshow.DTOs.Rating.RatingResponseDto;
import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.UserRating;
import com.scaler.bookMyshow.Services.Rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/movie")
    public RatingResponseDto rateMovies(@RequestBody RatingRequestDto requestDto){
        RatingResponseDto responseDto = new RatingResponseDto();
        try{
            UserRating rating = ratingService.rateMovie(requestDto.getUserId(), requestDto.getMovieId(), requestDto.getRating());
            responseDto.setUserRating(rating);
            responseDto.setStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("Your rating has been recorded");

        }catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("Some error occurred while rating movie");
        }
        return responseDto;
    }

    @GetMapping("/movie/{movieId}")
    public FetchRatingResponseDto getRating (@PathVariable int movieId){
        FetchRatingResponseDto responseDto = new FetchRatingResponseDto();
        try{
            double overallRating = ratingService.getMovieRating(movieId);
            responseDto.setOverallRating(overallRating);
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;

    }
}
