package com.company.DAO;

import com.company.Model.Movie;
import com.company.Model.User;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private MakeConnection makeConnection;

    public UserDAO(){
        makeConnection = new MakeConnection();
    }


    public ArrayList<User> getAll() {
        return null;
    }

    public boolean add(User newUser) {
        boolean result=false;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("insert into userapp values(?,?,?,?)");

            ps.setInt(1,newUser.getCPF());
            ps.setString(2,newUser.getName());
            ps.setString(3,newUser.getAddress());
            ps.setFloat(4,newUser.getSalary());

            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                ps = conn.prepareStatement("insert into login values(?,?,?)");
                ps.setString(1,newUser.getUserName());
                ps.setString(2,newUser.getPassword());
                ps.setInt(3,newUser.getCPF());
                ps.executeUpdate();

                ps = conn.prepareStatement("insert into leveluser values(?,?)");
                ps.setInt(1,newUser.getCPF());
                ps.setInt(2,newUser.getLevel());
                ps.executeUpdate();
                result = true;
            }
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            result = false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String remove(int id) {

        String result="Não é possível deletar o user";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from userapp where iduser = ?");
            ps.setInt(1,id);
            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "O user foi deletado com sucesso";
            }
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public User returnUser(int id) {
        User user= null;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from userapp where userid = ?");
            ps.setInt(1,id);
            ResultSet myResult  =ps.executeQuery();
            while (myResult.next()){
                user = new User();
                user.setCPF(myResult.getInt(1));
                user.setName(myResult.getString(2));
                user.setAddress(myResult.getString(3));
                user.setSalary(myResult.getFloat(4));
                System.out.println("Entrei no return User");
            }
            myResult.close();
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public int checkPasssUser(String password,String login) {
        int result= -1;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select userid from login where userpassword = ? and userlogin = ?");
            ps.setString(1,password);
            ps.setString(2,login);

            ResultSet myResult  =ps.executeQuery();
            while (myResult.next()){
                result = myResult.getInt(1);
            }
            myResult.close();
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int checkUserLevel(int userid){
        int result= -1;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select leveluser from leveluser where userid=?");
            ps.setInt(1,userid);
            ResultSet myResult  =ps.executeQuery();
            while (myResult.next()){
                result = myResult.getInt(1);
            }
            myResult.close();
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    public String update(String field, String value, int id) {
        return null;
    }
}
