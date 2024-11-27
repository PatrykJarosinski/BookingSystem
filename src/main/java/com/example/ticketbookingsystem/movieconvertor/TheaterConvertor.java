package com.example.ticketbookingsystem.movieconvertor;

import com.example.ticketbookingsystem.entity.Theater;
import com.example.ticketbookingsystem.request.TheaterRequest;

public class TheaterConvertor {

    public static Theater theaterDtoToTheater(TheaterRequest theaterRequest) {
        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();
        return theater;
    }
}
