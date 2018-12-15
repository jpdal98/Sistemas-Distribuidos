package com.company.Model;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Movie {
    private int code;
    private String title;
    private String description;
    private int quantity;
    private float valuemovie;


    public  Movie(){

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getValuemovie() {
        return valuemovie;
    }

    public void setValuemovie(float valuemovie) {
        this.valuemovie = valuemovie;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}