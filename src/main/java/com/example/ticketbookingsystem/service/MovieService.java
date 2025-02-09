package com.example.ticketbookingsystem.service;


import com.example.ticketbookingsystem.entity.Movie;
import com.example.ticketbookingsystem.exceptions.MovieAlreadyExist;
import com.example.ticketbookingsystem.movieconvertor.MovieConvertor;
import com.example.ticketbookingsystem.repo.MovieRepository;
import com.example.ticketbookingsystem.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieRequest movieRequest){
        Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());

        if(movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())){
            throw new MovieAlreadyExist();
        }

        Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);

        movieRepository.save(movie);


        return "Movie added succesfully";

    }

}
