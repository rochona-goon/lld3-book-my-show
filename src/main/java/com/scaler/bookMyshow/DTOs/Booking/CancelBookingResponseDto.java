package com.scaler.bookMyshow.DTOs.Booking;

import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.Booking;
import lombok.Data;

@Data
public class CancelBookingResponseDto {
    private int bookingId;
    private double bookingAmount;
    private ResponseStatus status;
}
