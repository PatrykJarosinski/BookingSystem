package com.example.ticketbookingsystem.service;

import com.example.ticketbookingsystem.entity.Movie;
import com.example.ticketbookingsystem.entity.Show;
import com.example.ticketbookingsystem.entity.Theater;
import com.example.ticketbookingsystem.exceptions.MovieDoesNotExists;
import com.example.ticketbookingsystem.exceptions.TheaterDoesNotExists;
import com.example.ticketbookingsystem.repo.MovieRepository;
import com.example.ticketbookingsystem.repo.ShowRepository;
import com.example.ticketbookingsystem.repo.TheaterRepository;
import com.example.ticketbookingsystem.request.ShowRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceTest {

    @Mock
    ShowRepository showRepository;

    @Mock
    MovieRepository movieRepository;

    @Mock
    TheaterRepository theaterRepository;

    @InjectMocks
    ShowService showService;


    @Test
    void addShow_insertCorrectShow_addShow(){

        //Movie
        Movie movie = new Movie();
        movie.setId(1);
        movie.setShows(new ArrayList<>());

        //Theater
        Theater theater = new Theater();
        theater.setId(1);
        theater.setShowList(new ArrayList<>());


        //Show
        Show show = new Show();
        show.setTheater(theater);
        show.setMovie(movie);
        show.setShowSeatList(null);
        show.setTicketList(null);

        //Show request
        ShowRequest showRequest = new ShowRequest();
        showRequest.setMovieId(1);
        showRequest.setTheaterId(1);

        //OPT

        Optional<Movie> movieOpt = Optional.of(movie);
        Optional<Theater> theaterOpt = Optional.of(theater);

        Mockito.when(movieRepository.findById(showRequest.getMovieId())).thenReturn(movieOpt);
        Mockito.when(theaterRepository.findById(showRequest.getTheaterId())).thenReturn(theaterOpt);

        Mockito.when(showRepository.save(show)).thenReturn(show);
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        Mockito.when(theaterRepository.save(theater)).thenReturn(theater);


        String result = showService.addShow(showRequest);

        Assertions.assertEquals("Show added succesfully",result);

        Mockito.verify(movieRepository, Mockito.times(1)).findById(1);
        Mockito.verify(theaterRepository, Mockito.times(1)).findById(1);
        Mockito.verify(showRepository, Mockito.times(1)).save(Mockito.any(Show.class));
        Mockito.verify(movieRepository, Mockito.times(1)).save(movie);
        Mockito.verify(theaterRepository, Mockito.times(1)).save(theater);

    }

    @Test
    void addShow_insertNotExistingMovie_throwMovieDoesNotExist(){
//Movie
        Movie movie = new Movie();
        movie.setId(1);
        movie.setShows(new ArrayList<>());

        //Theater
        Theater theater = new Theater();
        theater.setId(1);
        theater.setShowList(new ArrayList<>());


        //Show
        Show show = new Show();
        show.setTheater(theater);
        show.setMovie(movie);
        show.setShowSeatList(null);
        show.setTicketList(null);

        //Show request
        ShowRequest showRequest = new ShowRequest();
        showRequest.setMovieId(1);
        showRequest.setTheaterId(1);

        //OPT

        Optional<Movie> movieOpt = Optional.empty();

        Mockito.when(movieRepository.findById(showRequest.getMovieId())).thenReturn(movieOpt);

        Assertions.assertThrows(MovieDoesNotExists.class, () -> showService.addShow(showRequest));

        Mockito.verify(movieRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void addShow_insertNotExistingTheater_throwTheaterDoesNotExist(){
        //Movie
        Movie movie = new Movie();
        movie.setId(1);
        movie.setShows(new ArrayList<>());

        //Theater
        Theater theater = new Theater();
        theater.setId(1);
        theater.setShowList(new ArrayList<>());


        //Show
        Show show = new Show();
        show.setTheater(theater);
        show.setMovie(movie);
        show.setShowSeatList(null);
        show.setTicketList(null);

        //Show request
        ShowRequest showRequest = new ShowRequest();
        showRequest.setMovieId(1);
        showRequest.setTheaterId(1);

        //OPT

        Optional<Movie> movieOpt = Optional.of(movie);
        Optional<Theater> theaterOpt = Optional.empty();

        Mockito.when(movieRepository.findById(showRequest.getMovieId())).thenReturn(movieOpt);
        Mockito.when(theaterRepository.findById(showRequest.getTheaterId())).thenReturn(theaterOpt);



        Assertions.assertThrows(TheaterDoesNotExists.class, () -> showService.addShow(showRequest));

        Mockito.verify(movieRepository, Mockito.times(1)).findById(1);
        Mockito.verify(theaterRepository, Mockito.times(1)).findById(1);
    }

    //TODO testy associateShowSeats


}