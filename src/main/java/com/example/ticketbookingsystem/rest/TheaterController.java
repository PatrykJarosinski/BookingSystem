package com.example.ticketbookingsystem.rest;


import com.example.ticketbookingsystem.request.TheaterRequest;
import com.example.ticketbookingsystem.request.TheaterSeatRequest;
import com.example.ticketbookingsystem.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;


    @PostMapping("/addNew")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequest theaterRequest){
        try{
            String result = theaterService.addTheater(theaterRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addNewSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest theaterSeatRequest){
        try{
            String result = theaterService.addTheaterSeat(theaterSeatRequest);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
