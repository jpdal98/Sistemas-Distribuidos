package com.company.Factory;

import com.company.Model.Warehouse;

public class FactoryWareHouse implements Factory<Warehouse> {

    @Override
    public Warehouse create(String type) {
        if(type.equals("WareHouse")){
            return new Warehouse();
        }
        else{
            return null;
        }
    }
}
