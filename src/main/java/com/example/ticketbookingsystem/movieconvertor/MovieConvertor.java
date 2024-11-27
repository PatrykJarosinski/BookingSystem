package com.example.ticketbookingsystem.movieconvertor;

import com.example.ticketbookingsystem.entity.Movie;
import com.example.ticketbookingsystem.request.MovieRequest;

public class MovieConvertor {

    public static Movie movieDtoToMovie(MovieRequest movieRequest){
        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .duration(movieRequest.getDuration())
                .gengre(movieRequest.getGengre())
                .language(movieRequest.getLanguage())
                .releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating())
                .build();

        return movie;
    }
}
