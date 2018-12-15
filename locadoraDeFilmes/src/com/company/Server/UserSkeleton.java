package com.company.Server;

import com.company.DAO.UserDAO;
import com.company.Model.User;
import org.json.JSONObject;

public class UserSkeleton {


    public String insertUser(String menssage){

        boolean answer = false;
        JSONObject myObject  = new JSONObject(menssage);
        User user = new User();
        user.setCPF((Integer.parseInt((String) myObject.get("cpf"))));
        user.setName((String)myObject.get("name"));
        user.setAddress((String)myObject.get("address"));
        user.setSalary((Float.parseFloat((String) myObject.get("salary"))));
        user.setUserName((String)myObject.get("userName"));
        user.setPassword((String)myObject.get("password"));
        user.setLevel(1);

        UserDAO userDAO = new UserDAO();
        answer =  userDAO.add(user);

        myObject = new JSONObject();
        myObject.put("answer",answer);

        return myObject.toString();
    }
    public String checkPasssUser(String mensager){
        JSONObject decode = new JSONObject(mensager);
        UserDAO userDAO  = new UserDAO();
        int value= userDAO.checkPasssUser(decode.getString("password"),decode.getString("userLogin"));


        JSONObject encode = new JSONObject();
        encode.put("answer",value);
        return encode.toString();
    }
    public String checkUserLevel(String mensager){
        JSONObject decode = new JSONObject(mensager);
        UserDAO userDAO = new UserDAO();
        int result = userDAO.checkUserLevel(decode.getInt("userid"));

        JSONObject encode = new JSONObject();
        encode.put("answer",result);
        return encode.toString();

    }
    public String returnUser(String mensager){
        JSONObject decode = new JSONObject(mensager);
        UserDAO  userDAO = new UserDAO();
        User user = userDAO.returnUser(decode.getInt("userid"));

        JSONObject encode = new JSONObject();
        if(user ==null){
            encode.put("answer","no");
        }
        else{
            encode.put("answer","yes");
            encode.put("cpf",user.getCPF());
            encode.put("name",user.getName());
            encode.put("address",user.getAddress());
            encode.put("salary",user.getSalary());
        }


        return encode.toString();

    }

}
