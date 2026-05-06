package com.scaler.bookMyshow.Services;

import com.scaler.bookMyshow.Models.Booking;

public interface BookingService {
    Booking createBooking(int userId, int showId);
}
