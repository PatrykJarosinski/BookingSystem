package com.example.ticketbookingsystem.exceptions;

public class MovieAlreadyExist extends RuntimeException{

    private static final long serialVersionUID = 87214071728310561L;

    public MovieAlreadyExist(){
        super("Movie already exist with same name and language");
    }
}
