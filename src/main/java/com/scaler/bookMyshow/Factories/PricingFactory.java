package com.scaler.bookMyshow.Factories;

import com.scaler.bookMyshow.Strategies.PricingStrategy;
import com.scaler.bookMyshow.Strategies.SeasonalPricing;
import com.scaler.bookMyshow.Strategies.WeekdayPricing;
import com.scaler.bookMyshow.Strategies.WeekendPricing;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class PricingFactory {
    public PricingStrategy getPricingStrategy(Date date){
        PricingStrategy basePricing;

        if(isWeekend(date))
            basePricing =  new WeekendPricing(0.20);
        else
            basePricing =  new WeekdayPricing(0.10);


        return new SeasonalPricing(basePricing, 0.3);
    }

    private boolean isWeekend(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }

}
