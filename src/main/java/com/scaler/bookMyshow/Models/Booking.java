package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;
    private long bookingAmount;

    @ManyToOne
    private Show show;

    @OneToMany
    private List<ShowSeat> showSeats;

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;
    private Date bookingDate;
}
