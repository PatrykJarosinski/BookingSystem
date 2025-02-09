package com.example.ticketbookingsystem.service;

import com.example.ticketbookingsystem.entity.Theater;
import com.example.ticketbookingsystem.exceptions.TheaterIsExist;
import com.example.ticketbookingsystem.exceptions.TheaterIsNotExist;
import com.example.ticketbookingsystem.repo.TheaterRepository;
import com.example.ticketbookingsystem.request.TheaterRequest;
import com.example.ticketbookingsystem.request.TheaterSeatRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TheaterServiceTest {

    @Mock
    TheaterRepository theaterRepository;

    @InjectMocks
    TheaterService theaterService;

    //addTheater

    @Test
    void addTheater_insertCorrectTheater_addTheater(){
        TheaterRequest theaterRequest = new TheaterRequest();
        theaterRequest.setAddress("test123");

        Theater theater = new Theater();
        theater.setAddress("test123");
        theater.setShowList(null);
        theater.setTheaterSeatList(null);

        Mockito.when(theaterRepository.save(theater)).thenReturn(theater);

        String result = theaterService.addTheater(theaterRequest);

        Assertions.assertEquals("Theater has been added succesfully",result);

        Mockito.verify(theaterRepository, Mockito.times(1)).save(Mockito.any(Theater.class));
    }

    @Test
    void addTheater_insertExistingTheater_throwsTheaterIsExist(){
        TheaterRequest theaterRequest = new TheaterRequest();
        theaterRequest.setAddress("test123");

        Theater theater = new Theater();
        theater.setAddress("test123");
        theater.setShowList(null);
        theater.setTheaterSeatList(null);

        Mockito.when(theaterRepository.findByAddress(theaterRequest.getAddress())).thenReturn(theater);

        Assertions.assertThrows(TheaterIsExist.class, () -> theaterService.addTheater(theaterRequest));

        Mockito.verify(theaterRepository, Mockito.never()).save(Mockito.any(Theater.class));

    }

    //addThetaerSeat

    @Test
    void addTheaterSeat_insertCorrectData_addTheaterSeat(){
        TheaterSeatRequest theaterSeatRequest = new TheaterSeatRequest();
        theaterSeatRequest.setAddress("test1");
        theaterSeatRequest.setNoOfClassicSeat(1);
        theaterSeatRequest.setNoOfPremiumSeat(1);
        theaterSeatRequest.setNoOfSeatInRow(2);

        Theater theater = new Theater();
        theater.setAddress("test1");

        Mockito.when(theaterRepository.findByAddress(theaterSeatRequest.getAddress())).thenReturn(theater);

        String response = theaterService.addTheaterSeat(theaterSeatRequest);

        Assertions.assertEquals("Theater seats added succesfully",response);

        Mockito.verify(theaterRepository, Mockito.times(1)).save(Mockito.any(Theater.class));



    }

    @Test
    void addTheaterSeat_insertNotExistingTheater_throwTheaterIsNotExist(){
        TheaterSeatRequest theaterSeatRequest = new TheaterSeatRequest();
        theaterSeatRequest.setAddress(null);

        Mockito.when(theaterRepository.findByAddress(theaterSeatRequest.getAddress())).thenReturn(null);

        Assertions.assertThrows(TheaterIsNotExist.class, () -> theaterService.addTheaterSeat(theaterSeatRequest));

        Mockito.verify(theaterRepository, Mockito.never()).save(Mockito.any(Theater.class));
    }


}