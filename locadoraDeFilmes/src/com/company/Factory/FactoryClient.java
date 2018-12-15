package com.company.Factory;

import com.company.Model.Client;

public class FactoryClient implements Factory<Client> {
    @Override
    public Client create(String type) {

        if(type.equals("Client")){
            return new Client();
        }
        else{
            return null;
        }
    }
}
