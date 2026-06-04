package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
