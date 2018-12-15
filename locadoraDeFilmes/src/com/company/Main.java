package com.company;

import com.company.Screens.*;

public class Main {
    public static Screen screen = new InitialScreen();
    public static boolean flag = true;
    public static void main(String[] args) {
        while(flag){
            screen.show();
        }
    }
    public static void setScreen(Screen newScreen){
        screen = newScreen;
    }
}
