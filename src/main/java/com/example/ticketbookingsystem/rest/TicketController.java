package com.example.ticketbookingsystem.rest;

import com.example.ticketbookingsystem.request.TicketRequest;
import com.example.ticketbookingsystem.response.TicketResponse;
import com.example.ticketbookingsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<Object> ticketBooking(@RequestBody TicketRequest ticketRequest){
        try{
            TicketResponse result = ticketService.ticketBooking(ticketRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
