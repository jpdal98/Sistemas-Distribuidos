package com.company.Factory;

import com.company.Proxy.AdmProxy;
import com.company.Proxy.Proxy;
import com.company.Proxy.UserProxy;

public class FactoryProxy implements Factory<Proxy>{
    @Override
    public Proxy create(String type) {
        if(type.equals("UserProxy")){
            return new UserProxy();
        }
        else if(type.equals("AdmProxy")){
            return new AdmProxy();
        }

        else{
            return null;
        }
    }
}
