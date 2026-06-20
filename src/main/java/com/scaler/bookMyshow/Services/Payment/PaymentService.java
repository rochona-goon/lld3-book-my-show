package com.scaler.bookMyshow.Services.Payment;

import com.scaler.bookMyshow.Models.Payment;

public interface PaymentService {
    Payment makePayment(int bookingId);
}
