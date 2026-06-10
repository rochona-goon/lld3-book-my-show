package com.scaler.bookMyshow.Strategies;

import com.scaler.bookMyshow.Models.ShowSeatType;

import java.util.Date;

public class WeekendPricing implements PricingStrategy{
    private final double weekendMultiplier;

    public WeekendPricing(double weekendMultiplier) {
        this.weekendMultiplier = weekendMultiplier;
    }

    @Override
    public long calculatePrice(ShowSeatType seatPrice, Date date) {
        double difference = seatPrice.getSeatPrice() * weekendMultiplier ;
        return seatPrice.getSeatPrice() + (long)difference;
    }
}
