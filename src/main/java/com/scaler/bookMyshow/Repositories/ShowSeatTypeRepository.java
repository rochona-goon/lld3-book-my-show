package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Show;
import com.scaler.bookMyshow.Models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Integer> {
    List<ShowSeatType> findAllByShow(Show show);

}
