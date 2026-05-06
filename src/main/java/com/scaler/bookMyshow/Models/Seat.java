package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int rowVal;
    private int columnVal;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
}
