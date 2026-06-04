package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Show;
import com.scaler.bookMyshow.Models.ShowSeat;
import com.scaler.bookMyshow.Models.ShowSeatType;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from ShowSeat s where s.id in :ids")
    List<ShowSeat> findAllByIdWithLock(@Param("ids") List<Integer> ids);
}
