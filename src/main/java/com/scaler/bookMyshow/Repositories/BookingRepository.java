package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
