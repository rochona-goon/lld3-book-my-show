package com.scaler.bookMyshow.Models;

import com.scaler.bookMyshow.Enums.PaymentProvider;
import com.scaler.bookMyshow.Enums.PaymentMode;
import com.scaler.bookMyshow.Enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    @ManyToOne
    private Booking booking;

    @ManyToOne
    private User user;
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(value = EnumType.STRING)
    private PaymentProvider paymentProvider;

    @Enumerated(value = EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    private Date paymentDate;
    private String transactionNo;

}
