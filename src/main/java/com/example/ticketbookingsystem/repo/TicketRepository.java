package com.example.ticketbookingsystem.repo;

import com.example.ticketbookingsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
