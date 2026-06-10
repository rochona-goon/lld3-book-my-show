package com.scaler.bookMyshow.DTOs.Rating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequestDto {
    private int userId;
    private int movieId;
    private int rating;
}
