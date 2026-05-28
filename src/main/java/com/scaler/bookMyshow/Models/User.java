package com.scaler.bookMyshow.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String userName;
    private String userEmail;
    private String password;
//    private List<UserRating> userRatings;

}
