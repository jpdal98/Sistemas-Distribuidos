package com.company.Factory;

import com.company.Screens.MainScreenADM;
import com.company.Screens.MainScreenClerk;
import com.company.Screens.Screen;

public class FactoryScreen implements Factory<Screen> {


    @Override
    public Screen create(String type) {
        if(type.equals("InitialScreen")){
            return null;
        }
       else if(type.equals("MainScreenClerk")){
            return new MainScreenClerk();
        }
        else if(type.equals("MainScreenADM")){
            return new MainScreenADM();
        }
        else{
            return null;
        }
    }
}
