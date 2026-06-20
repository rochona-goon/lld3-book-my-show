package com.scaler.bookMyshow.Services.Notification;

import com.scaler.bookMyshow.Enums.BookingEventType;
import com.scaler.bookMyshow.Models.Booking;

public interface NotificationObserver {
    void notify(Booking booking, BookingEventType eventType);
}
