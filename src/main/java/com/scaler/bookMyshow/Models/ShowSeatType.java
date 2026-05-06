package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private long seatPrice;
}
