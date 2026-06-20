package com.scaler.bookMyshow.DTOs.Payment;

import com.scaler.bookMyshow.DTOs.ResponseStatus;
import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Models.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private double paymentAmount;
    private ResponseStatus status;
    private String message;
}
