package com.example.ticketbookingsystem.entity;


import com.example.ticketbookingsystem.enums.Gengre;
import com.example.ticketbookingsystem.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MOVIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String movieName;


    private Integer duration;

    @Column(scale = 2)
    private Double rating;

    private Date releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Gengre gengre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Show> shows = new ArrayList<>();
}
