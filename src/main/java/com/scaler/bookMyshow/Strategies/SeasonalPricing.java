package com.scaler.bookMyshow.Strategies;

import com.scaler.bookMyshow.Decorators.SeasonalDecorator;
import com.scaler.bookMyshow.Models.ShowSeatType;

import java.util.Date;
import java.util.HashSet;

public class SeasonalPricing implements SeasonalDecorator {
    private final PricingStrategy pricingStrategy;
    private final double seasonalMultiplier;
    // Seasonal dates can be loaded from DB/configuration in production
    private final HashSet<Date> seasonalDates = new HashSet<>();

    public SeasonalPricing(PricingStrategy pricingStrategy, double seasonalMultiplier) {
        this.pricingStrategy = pricingStrategy;
        this.seasonalMultiplier = seasonalMultiplier;
    }

    @Override
    public long calculatePrice(ShowSeatType seatPrice, Date date) {
        // Calculate the default pricing
        long price = pricingStrategy.calculatePrice(seatPrice, date);

        if(isSeasonalDate(date)){
            double difference =  price * seasonalMultiplier;
            price += (long)difference;
        }
        return price;
    }

    private boolean isSeasonalDate(Date date){
        return seasonalDates.contains(date);
    }
}
