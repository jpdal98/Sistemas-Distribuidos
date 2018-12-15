package com.company.Model;

import java.util.Date;

public class Rent {
    private int cpf;
    private int codeMovie;
    private String name;
    private String movieName;
    private String dateRent;
    private String dateToGiveBack;

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCodeMovie() {
        return codeMovie;
    }

    public void setCodeMovie(int codeMovie) {
        this.codeMovie = codeMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDateRent() {
        return dateRent;
    }

    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }

    public String getDateToGiveBack() {
        return dateToGiveBack;
    }

    public void setDateToGiveBack(String dateToGiveBack) {
        this.dateToGiveBack = dateToGiveBack;
    }
}
