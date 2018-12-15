package com.company.Proxy;

import com.company.Model.User;

public interface Proxy {

    void callConnection();
    void switchScreen(String screenName, User user);
}
