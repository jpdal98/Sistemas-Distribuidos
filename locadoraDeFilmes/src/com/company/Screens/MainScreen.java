package com.company.Screens;

import com.company.Model.User;

import java.util.Scanner;

public abstract class MainScreen extends Screen {
     private User user;
    @Override
    public abstract void show();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
