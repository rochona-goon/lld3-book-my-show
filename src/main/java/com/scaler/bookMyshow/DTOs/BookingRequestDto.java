package com.scaler.bookMyshow.DTOs;

import com.scaler.bookMyshow.Models.ShowSeat;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDto {
    private int userId;
    private int showId;
    private List<ShowSeat> showSeats;
}
