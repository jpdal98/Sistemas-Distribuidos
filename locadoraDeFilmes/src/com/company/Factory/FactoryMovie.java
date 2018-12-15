package com.company.Factory;

import com.company.Model.Movie;

public class FactoryMovie implements Factory<Movie> {

    @Override
    public Movie create(String type) {
        if(type.equals("Movie")){
            return new Movie();
        }
        else{
            return null;
        }
    }
}
