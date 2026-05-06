package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.ShowStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "Shows")
public class Show extends BaseModel{
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    @ManyToOne
    private Theatre theatre;

    @OneToMany
    private List<ShowSeat> showSeats;

    @OneToMany
    private List<ShowSeatType> showSeatTypes;

    @Enumerated(value = EnumType.STRING)
    private ShowStatus showStatus;

}
