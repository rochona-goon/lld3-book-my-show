package com.scaler.bookMyshow.Controllers;

import com.scaler.bookMyshow.DTOs.Booking.*;
import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Services.Booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/bookTicket")
    public BookingResponseDto bookTickets(@RequestBody BookingRequestDto requestDto) {
        BookingResponseDto responseDto = new BookingResponseDto();
        try{
            Booking booking = bookingService.createBooking(requestDto.getUserId(), requestDto.getShowId(), requestDto.getShowSeatIds());
            responseDto.setBookingId(booking.getId());
            responseDto.setAmount(booking.getBookingAmount());
            responseDto.setBookingStatus(BookingResponseStatus.SUCCESS);

        }catch(Exception e){
            responseDto.setBookingStatus(BookingResponseStatus.FAILURE);

        }
        return responseDto;
    }

    @PostMapping("/cancelTicket")
    public CancelBookingResponseDto cancelTickets(@RequestBody CancelBookingRequestDto requestDto) {
        CancelBookingResponseDto responseDto = new CancelBookingResponseDto();
        try{
            Booking booking = bookingService.cancelBooking(requestDto.getBookingId());
            responseDto.setBookingId(booking.getId());
            responseDto.setBookingAmount(booking.getBookingAmount());
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);

        }
        return responseDto;
    }
}
