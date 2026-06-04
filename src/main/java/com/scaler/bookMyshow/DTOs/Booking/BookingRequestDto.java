package com.scaler.bookMyshow.DTOs.Booking;

import com.scaler.bookMyshow.Models.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDto {
    private int userId;
    private int showId;
    private List<Integer> showSeatIds;
}
