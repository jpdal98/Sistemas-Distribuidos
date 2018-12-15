package com.company.Model;

import java.util.ArrayList;

public class Warehouse {

    private String name;
    private ArrayList <Client> clientList ;
    //private ArrayList <Clerk> userlist ;
    private ArrayList<User> listOfUser ;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList clientList) {
        this.clientList = clientList;
    }


    public ArrayList getListOfUser() {
        return listOfUser;
    }

    public void setListOfUser(ArrayList<User> listOfUser) {
        this.listOfUser = listOfUser;
    }
}
