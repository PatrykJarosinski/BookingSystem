package com.example.ticketbookingsystem.service;

import com.example.ticketbookingsystem.entity.Movie;
import com.example.ticketbookingsystem.enums.Gengre;
import com.example.ticketbookingsystem.enums.Language;
import com.example.ticketbookingsystem.exceptions.MovieAlreadyExist;
import com.example.ticketbookingsystem.movieconvertor.MovieConvertor;
import com.example.ticketbookingsystem.repo.MovieRepository;
import com.example.ticketbookingsystem.request.MovieRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;
    @InjectMocks
    MovieService movieService;


    @Test
    void addMovie_insertCorrectMovie_addedMovie(){
        //movieRequest
        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setMovieName("test1");
        movieRequest.setDuration(50);
        movieRequest.setRating(8.5);
        movieRequest.setLanguage(Language.ENGLISH);
        movieRequest.setGengre(Gengre.DRAMA);

        //movie
        Movie movie = new Movie();
        movie.setMovieName(movieRequest.getMovieName());
        movie.setDuration(movieRequest.getDuration());
        movie.setRating(movieRequest.getRating());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setGengre(movieRequest.getGengre());
        movie.setShows(null);

        Mockito.when(movieRepository.save(movie)).thenReturn(movie);

        String result = movieService.addMovie(movieRequest);

        Assertions.assertEquals("Movie added succesfully", result);
        Assertions.assertNotNull(movie);
        Assertions.assertNotNull(movieRequest);

        Mockito.verify(movieRepository, Mockito.times(1)).save(Mockito.any(Movie.class));

    }

    @Test
    void addMovie_sameMovie_throwMovieAlreadyExist(){
        MovieRequest movieRequest = new MovieRequest();

        movieRequest.setMovieName("test1");
        movieRequest.setLanguage(Language.ENGLISH);

        Movie existingMovie = new Movie();
        existingMovie.setMovieName(movieRequest.getMovieName());
        existingMovie.setLanguage(movieRequest.getLanguage());

        Mockito.when(movieRepository.findByMovieName(existingMovie.getMovieName())).thenReturn(existingMovie);

        Assertions.assertThrows(MovieAlreadyExist.class,() -> movieService.addMovie(movieRequest));

        Mockito.verify(movieRepository,Mockito.never()).save(Mockito.any(Movie.class));

    }


}