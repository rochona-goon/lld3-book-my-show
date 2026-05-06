package com.scaler.bookMyshow.Controllers;

import com.scaler.bookMyshow.DTOs.BookingRequestDto;
import com.scaler.bookMyshow.DTOs.BookingResponseDto;
import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    public BookingResponseDto bookTickets(BookingRequestDto requestDto) {
        return null;
    }
}
