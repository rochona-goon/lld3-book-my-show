package com.scaler.bookMyshow.Strategies;

import com.scaler.bookMyshow.Models.ShowSeatType;

import java.util.Date;

public interface PricingStrategy {
    long calculatePrice(ShowSeatType seatPrice, Date date);
}
