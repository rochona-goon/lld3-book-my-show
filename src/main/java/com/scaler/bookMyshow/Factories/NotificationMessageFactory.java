package com.scaler.bookMyshow.Factories;

import com.scaler.bookMyshow.Enums.BookingEventType;
import com.scaler.bookMyshow.Models.Booking;

public class NotificationMessageFactory {

    public String getMessage(Booking booking, BookingEventType eventType){
        if(eventType.equals(BookingEventType.BOOKING_CONFIRMED)){
            return "Booking confirmed for "+
                    booking.getShow().getMovie().getMovieTitle();
        }else if(eventType.equals(BookingEventType.BOOKING_CANCELLED)){
            return "Your booking has been cancelled for "+
                    booking.getShow().getMovie().getMovieTitle();
        }else{
            return "Payment failed. Booking could not be completed.";
        }
    }
}
