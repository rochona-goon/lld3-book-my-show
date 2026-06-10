package com.scaler.bookMyshow.DTOs.Rating;

import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.UserRating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponseDto {
    private UserRating userRating;
    private ResponseStatus status;
    private String message;
}
