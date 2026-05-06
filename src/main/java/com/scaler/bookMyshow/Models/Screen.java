package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.Feature;
import com.scaler.bookMyshow.Enums.ScreenStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private int screenNumber;
//    private List<Show> shows;
    @OneToMany
    private List<Seat> seats;

    @Enumerated(value = EnumType.STRING)
    private ScreenStatus screenStatus;

    @ElementCollection(targetClass = Feature.class)
    private List<Feature> features;
}
