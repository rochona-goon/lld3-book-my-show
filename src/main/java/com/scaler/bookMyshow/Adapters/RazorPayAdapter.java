package com.scaler.bookMyshow.Adapters;

import com.scaler.bookMyshow.Enums.PaymentStatus;
import com.scaler.bookMyshow.external_libraries.RazorPayApi;
import org.springframework.stereotype.Component;

@Component
public class RazorPayAdapter implements PaymentGateway{
    private final RazorPayApi razorPayApi;

    public RazorPayAdapter() {
        this.razorPayApi = new RazorPayApi();
    }

    @Override
    public PaymentResponse processPayment(double amount) {
        PaymentResponse response = new PaymentResponse();

        try{
            String txnId = razorPayApi.makePayment(amount);
            response.setTransactionId(txnId);
            response.setPaymentStatus(PaymentStatus.SUCCESS);
            response.setMessage("Payment processing has been initiated");

        } catch (Exception e) {
            response.setPaymentStatus(PaymentStatus.FAILURE);
            response.setMessage("Error occurred during payment processing");
        }

        return response;
    }
}
