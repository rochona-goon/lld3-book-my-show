package com.scaler.bookMyshow.DTOs.Rating;

import com.scaler.bookMyshow.DTOs.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchRatingResponseDto {
    private double overallRating;
    private ResponseStatus status;
}
