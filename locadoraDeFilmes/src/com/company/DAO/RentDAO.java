package com.company.DAO;

import com.company.Factory.Factory;
import com.company.Factory.FactoryClient;
import com.company.Factory.FactoryMovie;
import com.company.Factory.FactoryRent;
import com.company.Model.Client;
import com.company.Model.ClientsRents;
import com.company.Model.Movie;
import com.company.Model.Rent;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class RentDAO implements DAO<Rent> {
    private MakeConnection makeConnection;

    private Factory myFactory;


    public RentDAO(){
        makeConnection = new MakeConnection();
        myFactory = new FactoryRent();
    }
    @Override
    public ArrayList<Rent> getAll() {
    return null;
    }


    public ArrayList<ClientsRents> getALLClientsRents(){
        ArrayList<ClientsRents> clientsRents = null;
        try{
            Connection conn = makeConnection.makeConnection();
            clientsRents = new ArrayList<>();

            PreparedStatement ps = conn.prepareStatement("select R.cpf,C.clientname from rent R, client C where R.cpf = C.cpf");

            ResultSet rs = ps.executeQuery();
            ArrayList<Client> clients = new ArrayList<>();

            myFactory = new FactoryClient();
            Client clientG;
            ClientsRents clientsRents1G;

            while(rs.next()){
                clientG= ((FactoryClient) myFactory).create("Client");
                clientG.setCpf(rs.getInt(1));
                clientG.setName(rs.getString(2));
                clients.add(clientG);
            }
            myFactory = new FactoryMovie();
            Movie movieG;
            ps = conn.prepareStatement("select M.title, R.idmovie from rent R, movie M where R.cpf = ? and R.idmovie = M.idmovie");
            for (Client client: clients) {
                ps.setInt(1,client.getCpf());
                rs = ps.executeQuery();
                clientsRents1G = new ClientsRents();
                clientsRents1G.setClient(client);
                while(rs.next()){
                    movieG= ((FactoryMovie) myFactory).create("Movie");
                    movieG.setTitle(rs.getString(1));
                    movieG.setCode(rs.getInt(2));
                    clientsRents1G.addMovie(movieG);
                }
                clientsRents.add(clientsRents1G);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientsRents;
    }

    @Override
    public String add(Rent rent) {
        String result = "Not possible add the rent";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("insert into rent values(?,?,?,?)");
            ps.setInt(1,rent.getCpf());
            ps.setInt(2,rent.getCodeMovie());
            ps.setString(3,rent.getDateRent());
            ps.setString(4,rent.getDateToGiveBack());

            int row = ps.executeUpdate();
            if(row > 0){
                 result = "movie add with success";
            }
        }
      catch (PSQLException e)  {
            result="This rent is already added";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String removeNormal(int idmovie, int cpf){
        String result="Not possible to remove the rent";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from rent where idmovie = ? and cpf = ?");
            ps.setInt(1,idmovie);
            ps.setInt(2,cpf);
            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "Esse Aluguel foi excluido";
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
    public String remove(int idmovie){
        String result="Not possible to remove the rent because this movie code doesn't exists";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from rent where and idmovie = ?");
            ps.setInt(1,idmovie);

            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "The rent has been removed";
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
    public String removeByClient(int cpf){
        String result="Nao foi possivel fazer a remocao";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from rent where cpf = ?");
            ps.setInt(1,cpf);

            int numberRows = ps.executeUpdate();
            if(numberRows>0){
                result = "O aluguel foi removido";
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
    public Rent getObject(int idmovie) {
        return  null;
    }









    public ClientsRents getClientRents(int cpf){
        ClientsRents clientsRents = null;
        try{
            Connection conn = makeConnection.makeConnection();
            clientsRents = new ClientsRents();
            PreparedStatement ps = conn.prepareStatement("select C.cpf,C.clientname from client C where C.cpf = ?");
            ps.setInt(1,cpf);
            ResultSet rs = ps.executeQuery();

            myFactory = new FactoryClient();
            Client client = ((FactoryClient) myFactory).create("Client");
            while(rs.next()){
                client.setName(rs.getString(2));
                client.setCpf(rs.getInt(1));
            }
            clientsRents.setClient(client);

            ps = conn.prepareStatement("select M.title, R.idmovie from rent R, movie M where R.cpf = ? and R.idmovie = M.idmovie");
            ps.setInt(1,cpf);
            Movie movieG;
            myFactory = new FactoryMovie();
            rs = ps.executeQuery();
            while(rs.next()){
                movieG = ((FactoryMovie)myFactory).create("Movie");
                movieG.setCode(rs.getInt(2));
                movieG.setTitle(rs.getString(1));
                clientsRents.addMovie(movieG);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientsRents;
    }

    public Rent getInvoice(int idmovie, int cpf){
        Rent rent = null;
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from rent where idmovie = ? and cpf = ?");
            ps.setInt(1,idmovie);
            ps.setInt(2,cpf);

            ResultSet rs = ps.executeQuery();
            myFactory = new FactoryRent();
            rent = ((FactoryRent) myFactory).create("Rent");
            while (rs.next()){
                rent.setCpf(rs.getInt(1));
                rent.setCodeMovie(rs.getInt(2));
                rent.setDateRent(rs.getString(3));
                rent.setDateToGiveBack(rs.getString(4));
            }

        }catch (PSQLException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  rent;
    }



    @Override
    public String update(String field, String value, int id) {
        String result = "Not possible update this rent";
        try{
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("update ");



        }catch (PSQLException e){
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean canRentMovie(int cpf){
        boolean result= false;
        try{
            Connection conn = makeConnection.makeConnection();
            CallableStatement callF = conn.prepareCall("{call fnc_howmanyrents(?)}");
            callF.setInt(1,cpf);
            ResultSet resultSet =callF.executeQuery();
            int numberRents = 0;
            while (resultSet.next()){
                numberRents = resultSet.getInt(1);
            }
            if (numberRents < 6){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
