package com.example.ticketbookingsystem.request;


import com.example.ticketbookingsystem.enums.Gengre;
import com.example.ticketbookingsystem.enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class MovieRequest {
    private String movieName;
    private Integer duration;
    private Double rating;
    private Date releaseDate;
    private Gengre gengre;
    private Language language;

}
