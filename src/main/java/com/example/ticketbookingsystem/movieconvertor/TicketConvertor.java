package com.example.ticketbookingsystem.movieconvertor;


import com.example.ticketbookingsystem.entity.Show;
import com.example.ticketbookingsystem.entity.Ticket;
import com.example.ticketbookingsystem.response.TicketResponse;

import java.sql.Date;

public class TicketConvertor {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date((Date) show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
