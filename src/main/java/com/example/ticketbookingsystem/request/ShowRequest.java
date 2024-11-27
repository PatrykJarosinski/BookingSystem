package com.example.ticketbookingsystem.request;


import com.example.ticketbookingsystem.entity.Theater;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ShowRequest {
    private Time showStartTime;
    private Date showDate;
    private Integer theaterId;
    private Integer movieId;
}
