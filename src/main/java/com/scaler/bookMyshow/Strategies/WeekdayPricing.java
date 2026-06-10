package com.scaler.bookMyshow.Strategies;

import com.scaler.bookMyshow.Models.ShowSeatType;

import java.util.Date;

public class WeekdayPricing implements PricingStrategy{
    private final double weekdayMultiplier;

    public WeekdayPricing(double weekdayMultiplier) {
        this.weekdayMultiplier = weekdayMultiplier;
    }

    @Override
    public long calculatePrice(ShowSeatType seatPrice, Date date) {
        double difference = seatPrice.getSeatPrice() * weekdayMultiplier ;
        return seatPrice.getSeatPrice() + (long)difference;
    }
}
