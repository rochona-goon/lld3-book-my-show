package com.scaler.bookMyshow.Services.Payment;

import com.scaler.bookMyshow.Adapters.PaymentGateway;
import com.scaler.bookMyshow.Adapters.PaymentResponse;
import com.scaler.bookMyshow.Enums.*;
import com.scaler.bookMyshow.Exceptions.BookingNotFoundException;
import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Models.Payment;
import com.scaler.bookMyshow.Repositories.BookingRepository;
import com.scaler.bookMyshow.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;

    @Autowired
    public PaymentServiceImpl(BookingRepository bookingRepository,
                              PaymentRepository paymentRepository,
                              PaymentGateway paymentGateway) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
    }

    @Override
    @Transactional
    public Payment makePayment(int bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()){
            throw new BookingNotFoundException("Booking not found");
        }
        Booking booking = optionalBooking.get();

        Payment payment = new Payment();
        payment.setAmount((double)booking.getBookingAmount());
        payment.setPaymentDate(new Date());
        payment.setPaymentMode(PaymentMode.UPI);
        payment.setBooking(booking);
        payment.setUser(booking.getUser());
        payment.setPaymentStatus(PaymentStatus.INITIATED);


        PaymentResponse response = this.paymentGateway.processPayment(booking.getBookingAmount());

        if(response.getTransactionId() != null){
            payment.setTransactionNo(response.getTransactionId());
        }

        if(response.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);

            booking.getShowSeats().forEach(showSeat -> {
                showSeat.setSeatStatus(SeatStatus.BOOKED);
            });

            booking.setBookingStatus(BookingStatus.CONFIRMED);

        }else{
            payment.setPaymentStatus(PaymentStatus.FAILURE);

            booking.getShowSeats().forEach(showSeat -> {
                showSeat.setSeatStatus(SeatStatus.AVAILABLE);
            });

            booking.setBookingStatus(BookingStatus.CANCELLED);
        }

        paymentRepository.save(payment);
        bookingRepository.save(booking);


        return payment;
    }
}
