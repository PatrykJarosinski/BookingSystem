package com.example.ticketbookingsystem.service;

import com.example.ticketbookingsystem.entity.Theater;
import com.example.ticketbookingsystem.entity.TheaterSeat;
import com.example.ticketbookingsystem.enums.SeatType;
import com.example.ticketbookingsystem.exceptions.TheaterIsExist;
import com.example.ticketbookingsystem.exceptions.TheaterIsNotExist;
import com.example.ticketbookingsystem.movieconvertor.TheaterConvertor;
import com.example.ticketbookingsystem.repo.TheaterRepository;
import com.example.ticketbookingsystem.request.TheaterRequest;
import com.example.ticketbookingsystem.request.TheaterSeatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public String addTheater(TheaterRequest theaterRequest) throws TheaterIsExist {

        if(theaterRepository.findByAddress(theaterRequest.getAddress()) != null){
            throw new TheaterIsExist();
        }
        Theater theater = TheaterConvertor.theaterDtoToTheater(theaterRequest);

        theaterRepository.save(theater);

        return "Theater has been added succesfully";
    }

    public String addTheaterSeat(TheaterSeatRequest entryDto) throws TheaterIsNotExist{
        if(theaterRepository.findByAddress(entryDto.getAddress())==null){
            throw new TheaterIsNotExist();
        }

        Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
        Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
        Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
        String address = entryDto.getAddress();
        Theater theater = theaterRepository.findByAddress(address);

        List<TheaterSeat> seatList = theater.getTheaterSeatList();

        int counter = 1;
        int fill = 0;
        char ch = 'A';

        for (int i = 1; i <= noOfClassicSeat; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        for (int i = 1; i <= noOfPremiumSeats; i++) {
            String seatNo = Integer.toString(counter) + ch;

            ch++;
            fill++;
            if (fill == noOfSeatsInRow) {
                fill = 0;
                counter++;
                ch = 'A';
            }

            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeat.setTheater(theater);
            seatList.add(theaterSeat);
        }

        theaterRepository.save(theater);

        return "Theater seats added succesfully";
    }

}
