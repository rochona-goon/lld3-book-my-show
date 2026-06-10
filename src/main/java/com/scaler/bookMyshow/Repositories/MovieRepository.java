package com.scaler.bookMyshow.Repositories;

import com.scaler.bookMyshow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
