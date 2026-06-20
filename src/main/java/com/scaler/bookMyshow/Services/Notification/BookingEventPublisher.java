package com.scaler.bookMyshow.Services.Notification;

import com.scaler.bookMyshow.Enums.BookingEventType;
import com.scaler.bookMyshow.Models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingEventPublisher {
    private final List<NotificationObserver> observers;

    @Autowired
    public BookingEventPublisher(List<NotificationObserver> observers) {
        this.observers = observers;
    }

    public void publish(Booking  booking, BookingEventType eventType){
        for(NotificationObserver ob : observers){
            ob.notify(booking,eventType);
        }
    }
}
