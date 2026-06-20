package com.scaler.bookMyshow.Services.Payment;

import com.scaler.bookMyshow.Adapters.PaymentGateway;
import com.scaler.bookMyshow.Adapters.PaymentResponse;
import com.scaler.bookMyshow.Enums.*;
import com.scaler.bookMyshow.Exceptions.BookingNotFoundException;
import com.scaler.bookMyshow.Models.Booking;
import com.scaler.bookMyshow.Models.Payment;
import com.scaler.bookMyshow.Repositories.BookingRepository;
import com.scaler.bookMyshow.Repositories.PaymentRepository;
import com.scaler.bookMyshow.Services.Notification.BookingEventPublisher;
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
    private final BookingEventPublisher bookingEventPublisher; //

    @Autowired
    public PaymentServiceImpl(BookingRepository bookingRepository,
                                PaymentRepository paymentRepository,
                                PaymentGateway paymentGateway, BookingEventPublisher bookingEventPublisher) {
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
        this.bookingEventPublisher = bookingEventPublisher;
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

        paymentRepository.save(payment);

        PaymentResponse response = this.paymentGateway.processPayment(booking.getBookingAmount());

        if(response.getTransactionId() != null){
            payment.setTransactionNo(response.getTransactionId());
        }

        BookingEventType eventType;
        if(response.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);

            booking.getShowSeats().forEach(showSeat -> {
                showSeat.setSeatStatus(SeatStatus.BOOKED);
                showSeat.setBlockedAt(null);

            });

            booking.setBookingStatus(BookingStatus.CONFIRMED);
            eventType = BookingEventType.BOOKING_CONFIRMED;

        }else{
            payment.setPaymentStatus(PaymentStatus.FAILURE);

            booking.getShowSeats().forEach(showSeat -> {
                showSeat.setSeatStatus(SeatStatus.AVAILABLE);
                showSeat.setBlockedAt(null);
            });

            booking.setBookingStatus(BookingStatus.CANCELLED);
            eventType = BookingEventType.PAYMENT_FAILED;
        }

        payment = paymentRepository.save(payment);
        Booking savedBooking = bookingRepository.save(booking);

        bookingEventPublisher.publish(savedBooking,eventType);


        return payment;
    }
}
