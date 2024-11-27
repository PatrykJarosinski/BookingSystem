package com.example.ticketbookingsystem.movieconvertor;

import com.example.ticketbookingsystem.entity.Show;
import com.example.ticketbookingsystem.request.ShowRequest;

public class ShowConvertor {
    public static Show showDtoToShow(ShowRequest showRequest){
        Show show = Show.builder()
                .time(showRequest.getShowStartTime())
                .date(showRequest.getShowDate())
                .build();

        return show;
    }
}
