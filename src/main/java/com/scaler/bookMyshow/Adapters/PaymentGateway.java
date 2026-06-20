package com.scaler.bookMyshow.Adapters;

public interface PaymentGateway {
    PaymentResponse processPayment(double amount);
}
