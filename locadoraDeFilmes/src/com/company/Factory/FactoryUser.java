package com.company.Factory;

import com.company.Model.User;

public class FactoryUser implements Factory<User> {

    public User create(String type){
        if(type.equals("User")){
            return new User();
        }
        else{
            return null;
        }
    }

}
