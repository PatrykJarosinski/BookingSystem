package com.example.ticketbookingsystem.repo;

import com.example.ticketbookingsystem.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String name);
}
