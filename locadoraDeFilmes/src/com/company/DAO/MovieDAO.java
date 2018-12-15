package com.company.DAO;

import com.company.Factory.Factory;
import com.company.Factory.FactoryClient;
import com.company.Factory.FactoryMovie;
import com.company.Model.Client;
import com.company.Model.Movie;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO implements DAO<Movie> {

    private Factory<Movie> myFactory;
    private MakeConnection makeConnection;

    public MovieDAO(){
        makeConnection = new MakeConnection();
        myFactory= new FactoryMovie();
    }

    @Override
    public ArrayList<Movie> getAll() {
        ArrayList<Movie> arrayMovie = new ArrayList<>();
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from movie");

            ResultSet myResult  =ps.executeQuery();


            Movie movieG;
            while (myResult.next()){
                movieG = myFactory.create("Movie");
                movieG.setCode(myResult.getInt(1));
                movieG.setTitle(myResult.getString(2));
                movieG.setDescription(myResult.getString(3));
                movieG.setQuantity(myResult.getInt(4));
                movieG.setValuemovie(myResult.getFloat(5));
                arrayMovie.add(movieG);
            }
            myResult.close();
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch (PSQLException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return arrayMovie;
    }

    @Override
    public String add(Movie movie) {
        String result="Not possible to add the movie";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("insert into movie values(?,?,?,?,?)");

            ps.setInt(1,movie.getCode());
            ps.setString(2,movie.getTitle());
            ps.setString(3,movie.getDescription());
            ps.setInt(4,movie.getQuantity());
            ps.setFloat(5,movie.getValuemovie());

            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "The movie has been added";
            }
            ps.close();
            conn.close();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        catch (PSQLException e){
            result = "This code movie already exists";
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String remove(int id) {
        String result="Não é possível deletar o filme";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from movie where idmovie = ?");
            ps.setInt(1,id);
            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "O filme foi deletado com sucesso";
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

    @Override
    public Movie getObject(int id) {
        Movie movie= null;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from movie where idmovie = ?");
            ps.setInt(1,id);
            ResultSet myResult  =ps.executeQuery();
            while (myResult.next()){
                movie = myFactory.create("Movie");
                movie.setCode(myResult.getInt(1));
                movie.setTitle(myResult.getString(2));
                movie.setDescription(myResult.getString(3));
                movie.setQuantity(myResult.getInt(4));
                movie.setValuemovie(myResult.getFloat(5));
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
        return movie;
    }




    public String update(String field, String value, int idmovie) {
       return null;
    }
    public String updateMovieQuantity(int quantity,int idmovie){
        String result= "Não foi possível atualizar a quantidade do filme";
        try{

            Connection conn = makeConnection.makeConnection();
            int newValue;


            PreparedStatement ps = conn.prepareStatement("update movie set quantity = ? where idmovie = ?");
            ps.setInt(1,quantity);
            ps.setInt(2,idmovie);
            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "A quantiade do filme foi atualizada filme foi atualizado!";
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
    public String updateMoviePrice(float price,int idmovie){
        String result= "Não foi possível atualizar a quantidade do filme";
        try{

            Connection conn = makeConnection.makeConnection();
            int newValue;


            PreparedStatement ps = conn.prepareStatement("update movie set movievalue = ? where idmovie = ?");
            ps.setFloat(1,price);
            ps.setInt(2,idmovie);
            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "O preço do filme foi atualizado!";
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

    public boolean movieHasQuantity(int idcode){
        boolean result = false;
        try{



            Connection conn = makeConnection.makeConnection();
            CallableStatement proc = conn.prepareCall("{call fnc_moviequantity(?)}");
            proc.setInt(1,idcode);
            ResultSet rs=  proc.executeQuery();

            int value = 0;
            while (rs.next()){
                value = rs.getInt(1);
            }
            System.out.println(value);
            if(value > 0){
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean thisMoviesExists(int idmovie){
        boolean result = false;
        try{
            Connection conn = makeConnection.makeConnection();
            CallableStatement proc = conn.prepareCall("{call fnc_movieexists(?)}");
            proc.setInt(1,idmovie);
            ResultSet rs=  proc.executeQuery();

            int value = 0;
            while (rs.next()){
                value = rs.getInt(1);
            }
            if(value > 0){
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
