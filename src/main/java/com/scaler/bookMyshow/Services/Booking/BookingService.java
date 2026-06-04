package com.scaler.bookMyshow.Services.Booking;

import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Models.ShowSeat;

import java.util.List;

public interface BookingService {
    Booking createBooking(int userId, int showId, List<Integer> showSeatIds);

    Booking  cancelBooking(int bookingId);
}
