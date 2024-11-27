package com.example.ticketbookingsystem.repo;

import com.example.ticketbookingsystem.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
