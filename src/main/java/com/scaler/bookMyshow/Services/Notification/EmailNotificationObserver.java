package com.scaler.bookMyshow.Services.Notification;

import com.scaler.bookMyshow.Enums.BookingEventType;
import com.scaler.bookMyshow.Factories.NotificationMessageFactory;
import com.scaler.bookMyshow.Models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver implements NotificationObserver {
    private final NotificationMessageFactory notificationMessageFactory;

    @Autowired
    public EmailNotificationObserver(NotificationMessageFactory notificationMessageFactory) {
        this.notificationMessageFactory = notificationMessageFactory;
    }

    @Override
    public void notify(Booking booking, BookingEventType eventType) {
        String message = notificationMessageFactory.getMessage(booking, eventType);
        System.out.println("Email notification sent to "+booking.getUser().getUserEmail()+" :"+ message);
    }
}
