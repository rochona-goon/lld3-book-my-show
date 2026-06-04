package com.scaler.bookMyshow.Services.Booking;

import com.scaler.bookMyshow.Enums.BookingStatus;
import com.scaler.bookMyshow.Enums.SeatStatus;
import com.scaler.bookMyshow.Enums.SeatType;
import com.scaler.bookMyshow.Exceptions.BookingNotFoundException;
import com.scaler.bookMyshow.Exceptions.SeatNotAvailableException;
import com.scaler.bookMyshow.Exceptions.ShowNotFoundException;
import com.scaler.bookMyshow.Exceptions.UserNotFoundException;
import com.scaler.bookMyshow.Models.*;
import com.scaler.bookMyshow.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService{
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public BookingServiceImpl(UserRepository userRepository,
                                ShowRepository showRepository,
                                BookingRepository bookingRepository,
                                ShowSeatRepository showSeatRepository,
                              ShowSeatTypeRepository showSeatTypeRepository){

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;

    }
    @Override
    @Transactional
    public Booking createBooking(int userId, int showId, List<Integer> showSeatIds) {
        // Check if user exists
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User currentUser = optionalUser.get();

        // Check if show exists
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new ShowNotFoundException("Show not found");
        }
        Show currentShow = showOptional.get();

//        boolean isNotAvailable = showSeats.stream()
//                .anyMatch(s -> !(s.getSeatStatus().equals(SeatStatus.AVAILABLE)));

        // Check if the show seats are available
        List<String> blockedSeats = new ArrayList<>();
        List<ShowSeat> showSeats = showSeatRepository.findAllByIdWithLock(showSeatIds);
        if(showSeats.size() != showSeatIds.size()){
            throw  new SeatNotAvailableException("One or more selected seat Ids are invalid");
        }

        for(ShowSeat seat : showSeats){
            if(!seat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
                blockedSeats.add(seat.getSeat().getRowVal()+" "+seat.getSeat().getColumnVal()+", ");
            }
        }


        if(!blockedSeats.isEmpty()){
            throw  new SeatNotAvailableException("Seats "+blockedSeats+" are not available");
        }

        // Block seats if available
        for(ShowSeat seat : showSeats){
            seat.setSeatStatus(SeatStatus.BLOCKED);
        }

        showSeatRepository.saveAll(showSeats);

        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(currentShow);


        // Convert to Map for faster lookup: SeatType -> Price
        Map<SeatType, Long> priceMap = new HashMap<>();
        for (ShowSeatType sst : showSeatTypes) {
            priceMap.put(sst.getSeatType(), sst.getSeatPrice());
        }



        long bookingAmount = 0;

        for(ShowSeat showSeat : showSeats) {
             // Get price directly from Map
            SeatType type = showSeat.getSeat().getSeatType();
            if(!priceMap.containsKey(type)){
                throw new IllegalArgumentException("pricing not configured for seat type: "+ type);
            }
            bookingAmount += priceMap.get(type);
        }


        Booking booking = new Booking();
        booking.setShow(currentShow);
        booking.setUser(currentUser);
        booking.setPayments(new ArrayList<>()); // initially empty
        booking.setShowSeats(showSeats);
        booking.setBookingAmount(bookingAmount);
        booking.setBookingDate(new Date());
        booking.setBookingStatus(BookingStatus.UNPAID);

        return bookingRepository.save(booking);

    }

    @Override
    @Transactional
    public Booking cancelBooking(int bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if(bookingOptional.isEmpty()){
            throw new BookingNotFoundException("Booking not found");
        }
        Booking booking = bookingOptional.get();

        List<ShowSeat> showSeats = booking.getShowSeats();
        List<Integer> showSeatIds = showSeats.stream().map(ShowSeat::getId).toList();
        List<ShowSeat> lockedSeats = showSeatRepository.findAllByIdWithLock(showSeatIds);
        for(ShowSeat showSeat : lockedSeats){
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        }
        showSeatRepository.saveAll(lockedSeats);

        double bookingAmount = booking.getBookingAmount(); // Return to User source

        booking.setBookingStatus(BookingStatus.CANCELLED);

        return bookingRepository.save(booking);
    }
}
