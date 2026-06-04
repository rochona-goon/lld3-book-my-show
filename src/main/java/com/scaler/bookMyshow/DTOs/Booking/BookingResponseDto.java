package com.scaler.bookMyshow.DTOs.Booking;

import com.scaler.bookMyshow.Enums.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {
    private int bookingId;
    private double amount;

    @Enumerated(value = EnumType.STRING)
    private BookingResponseStatus bookingStatus;
}
