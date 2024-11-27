package com.example.ticketbookingsystem.repo;

import com.example.ticketbookingsystem.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}
