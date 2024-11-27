package com.example.ticketbookingsystem.service;


import com.example.ticketbookingsystem.entity.Show;
import com.example.ticketbookingsystem.entity.ShowSeat;
import com.example.ticketbookingsystem.entity.Ticket;
import com.example.ticketbookingsystem.entity.User;
import com.example.ticketbookingsystem.exceptions.SeatsNotAvailable;
import com.example.ticketbookingsystem.exceptions.ShowDoesNotExists;
import com.example.ticketbookingsystem.exceptions.UserDoesNotExists;
import com.example.ticketbookingsystem.movieconvertor.TicketConvertor;
import com.example.ticketbookingsystem.repo.ShowRepository;
import com.example.ticketbookingsystem.repo.TicketRepository;
import com.example.ticketbookingsystem.repo.UserRepository;
import com.example.ticketbookingsystem.request.TicketRequest;
import com.example.ticketbookingsystem.response.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;


    public TicketResponse ticketBooking(TicketRequest ticketRequest) throws SeatsNotAvailable, UserDoesNotExists, ShowDoesNotExists{
        Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());
        if(showOpt.isEmpty()){
            throw new ShowDoesNotExists();
        }

        Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

        if(userOpt.isEmpty()){
            throw new UserDoesNotExists();
        }

        User user = userOpt.get();
        Show show = showOpt.get();

        Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(),ticketRequest.getRequestSeats());

        if(!isSeatAvailable){
            throw new SeatsNotAvailable();
        }

        // count price
        Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(),	ticketRequest.getRequestSeats());

        String seats = listToString(ticketRequest.getRequestSeats());

        Ticket ticket = new Ticket();
        ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
        ticket.setBookedSeats(seats);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        userRepository.save(user);
        showRepository.save(show);

        return TicketConvertor.returnTicket(show, ticket);
    }

    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();

            if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
                return false;
            }
        }

        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        Integer totalAmount = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }

        return totalAmount;
    }

    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }


}
