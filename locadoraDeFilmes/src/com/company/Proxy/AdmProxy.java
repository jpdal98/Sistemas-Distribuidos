package com.company.Proxy;

import com.company.Model.Client;
import com.company.Model.Movie;
import com.company.Model.User;
import com.company.TCPClient.TCPClient;
import org.json.JSONObject;

public class AdmProxy implements Proxy {
    TCPClient myTCP =  TCPClient.getInstance();

    @Override
    public void callConnection() {

    }

    @Override
    public void switchScreen(String screenName, User user) {

    }


    public boolean addUser(String userName, String userCPF, String userLogin,String userPassword, int level){
        boolean result;
        JSONObject mensager = new JSONObject();
        mensager.put("Class: ","ADM");
        mensager.put("Method: ","addUser");
        mensager.put("name: ",userName);
        mensager.put("cpf ",userCPF);
        mensager.put("login: ",userLogin);
        mensager.put("password: ",userPassword);
        mensager.put("level",level);
        System.out.println(mensager.toString());
        myTCP.send(mensager.toString());


        return false;
    }
    public boolean addMovie(String movie){
        return false;
    }
    public boolean addClient(String client){
        return false;
    }

    public boolean updateUser(String user){
        return false;
    }

    public boolean updateClient(String client){
        return false;
    }

    public boolean updateMovie(String movie){
        return false;
    }
    //////////////////////////////////////////////
    public boolean removeClient(int idClient){
        return false;
    }
    public boolean removeMovie(int idMovie){
        return false;
    }
    public boolean removeUser(int idUser){
        return false;
    }
    ////////////////////////////////////////////
    public Movie searchMovie(int idMovie){
        return null;
    }

    public Client searchClient(int idClient){
        return null;
    }

    public User searchUser(int idUser){
        return null;
    }

}
