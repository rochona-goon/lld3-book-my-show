package com.scaler.bookMyshow.Adapters;

import com.scaler.bookMyshow.Enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String transactionId;
    private PaymentStatus paymentStatus;
    private String message;

}
