package com.company.Proxy;

import com.company.Factory.FactoryScreen;
import com.company.Main;
import com.company.Model.User;
import com.company.Screens.MainScreen;
import com.company.Screens.MainScreenADM;
import com.company.Screens.MainScreenClerk;
import com.company.Screens.Screen;
import com.company.TCPClient.TCPClient;
import org.json.JSONObject;

public class UserProxy implements Proxy{

    private TCPClient connection;
    private FactoryScreen myFactory;

    public UserProxy(){
        connection = TCPClient.getInstance();
    }
    public void sair(){
        connection.closeConnection();
    }
    public int checkPasssUser(String userLogin, String password){
        int result =-1;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userLogin",userLogin);
        jsonObject.put("password",password);
        jsonObject.put("method","checkPasssUser");
        jsonObject.put("class","User");

        connection.send(jsonObject.toString());

        String answer = connection.receive();
        JSONObject decode = new JSONObject(answer);
        result = decode.getInt("answer");
        return result;
    }
    public boolean insertUser(String menssage){
        String [] userDefinition = menssage.split(",");
        JSONObject myObject  = new JSONObject();

        myObject.put("name",userDefinition[1]);
        myObject.put("cpf",userDefinition[0]);
        myObject.put("userName",userDefinition[2]);
        myObject.put("password",userDefinition[3]);
        myObject.put("address",userDefinition[4]);
        myObject.put("level",1);
        myObject.put("salary","500");
        myObject.put("method","insertUser");
        myObject.put("class","User");


        connection.send(myObject.toString());
        String  answer = connection.receive();
        JSONObject answerServer = new JSONObject(answer);
        return answerServer.getBoolean("answer");
    }

    public int checkUserLevel(int userID){
        JSONObject encode  = new JSONObject();
        encode.put("userid",userID);
        encode.put("class","User");
        encode.put("method","checkUserLevel");
        connection.send(encode.toString());

        String result = connection.receive();
        JSONObject decode = new JSONObject(result);
        return decode.getInt("answer");

    }public User returnUser(int userId){
        JSONObject encode = new JSONObject();
        encode.put("userid",userId);
        encode.put("class","User");
        encode.put("method","returnUser");
        connection.send(encode.toString());

        String result = connection.receive();
        JSONObject decode = new JSONObject(result);
        User user;
        if(decode.get("answer").equals("yes")){
            user = new User();
            user.setSalary(decode.getFloat("salary"));
            user.setAddress(decode.getString("address"));
            user.setCPF(decode.getInt("cpf"));
            user.setName(decode.getString("name"));
        }
        else {
            user = null;
        }
        return user;
    }


    @Override
    public void callConnection() {

    }

    @Override
    public void switchScreen(String screenName, User user) {
        myFactory = new FactoryScreen();
        Screen newScreen = ((FactoryScreen)myFactory).create(screenName);
        if (screenName.equals("MainScreenClerk")){
            ((MainScreenClerk)newScreen).setUser(user);
        }
        else{
            ((MainScreenADM)newScreen).setUser(user);
        }

        Main.screen = newScreen;
    }
}
