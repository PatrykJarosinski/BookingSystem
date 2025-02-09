package com.example.ticketbookingsystem.service;


import com.example.ticketbookingsystem.entity.*;
import com.example.ticketbookingsystem.enums.SeatType;
import com.example.ticketbookingsystem.exceptions.MovieDoesNotExists;
import com.example.ticketbookingsystem.exceptions.ShowDoesNotExists;
import com.example.ticketbookingsystem.exceptions.TheaterDoesNotExists;
import com.example.ticketbookingsystem.movieconvertor.ShowConvertor;
import com.example.ticketbookingsystem.repo.MovieRepository;
import com.example.ticketbookingsystem.repo.ShowRepository;
import com.example.ticketbookingsystem.repo.TheaterRepository;
import com.example.ticketbookingsystem.request.ShowRequest;
import com.example.ticketbookingsystem.request.ShowSeatRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    private final TheaterRepository theaterRepository;

    private final MovieRepository movieRepository;



    public String addShow(ShowRequest showRequest){

        Show show = ShowConvertor.showDtoToShow(showRequest);

        Optional<Movie> movieOpt = movieRepository.findById(showRequest.getMovieId());

        if(movieOpt.isEmpty()){
            throw new MovieDoesNotExists();
        }


        Optional<Theater> theaterOpt = theaterRepository.findById(showRequest.getTheaterId());

        if(theaterOpt.isEmpty()){
            throw new TheaterDoesNotExists();
        }

        Theater theater = theaterOpt.get();
        Movie movie = movieOpt.get();

        show.setTheater(theater);
        show.setMovie(movie);
        show = showRepository.save(show);

        movie.getShows().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show added succesfully";
    }

    public String associateShowSeats(ShowSeatRequest showSeatRequest){
        Optional<Show> showOpt = showRepository.findById(showSeatRequest.getShowId());

        if(showOpt.isEmpty()){
            throw new ShowDoesNotExists();
        }

        Show show = showOpt.get();

        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showSeatRequest.getPriceOfClassicSeat());
            } else{
                showSeat.setPrice(showSeatRequest.getPriceOfPremiumSeat());
            }

            showSeat.setShow(show);
            showSeat.setIsAvailable(Boolean.TRUE);
            showSeat.setIsFoodContains(Boolean.FALSE);

            showSeatList.add(showSeat);
        }

        showRepository.save(show);

        return "show seats have been associated succesfully";
    }

}
