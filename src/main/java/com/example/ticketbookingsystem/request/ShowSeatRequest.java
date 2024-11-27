package com.example.ticketbookingsystem.request;


import com.example.ticketbookingsystem.enums.SeatType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ShowSeatRequest {
    private Integer showId;
    private Integer priceOfPremiumSeat;
    private Integer priceOfClassicSeat;
}
