package com.company.Factory;

import com.company.Model.Rent;

public class FactoryRent implements Factory<Rent>{
    @Override
    public Rent create(String type) {
        if(type.equals("Rent")){
            return new Rent();
        }
        return null;
    }
}
