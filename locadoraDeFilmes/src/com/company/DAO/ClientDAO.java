package com.company.DAO;

import com.company.Factory.Factory;
import com.company.Factory.FactoryClient;
import com.company.Model.Client;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
public class ClientDAO implements DAO<Client> {
    private Factory<Client> myFactory;
    private MakeConnection makeConnection;

    public ClientDAO() {
        makeConnection = new MakeConnection();
        myFactory = new FactoryClient();
    }

    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> arrayClient = new ArrayList<>();
        try {
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from client");

            ResultSet myResult = ps.executeQuery();


            Client clientG;
            while (myResult.next()) {
                clientG = myFactory.create("Client");
                clientG.setCpf(myResult.getInt(1));
                clientG.setName(myResult.getString(2));
                clientG.setAddress(myResult.getString(3));

                arrayClient.add(clientG);
            }
            myResult.close();
            ps.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (PSQLException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return arrayClient;
    }

    @Override
    public String add(Client client) {
        String result = "Not possible to add the client";
        try {
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("insert into client values(?,?,?)");
            ps.setInt(1, client.getCpf());
            ps.setString(2, client.getName());
            ps.setString(3, client.getAddress());

            int numberRows = ps.executeUpdate();
            if (numberRows > 0) {
                result = "Client has been added";
            }
            ps.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (PSQLException e) {
            //Put one String here
            result = "This cpf already exists in our system";
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String remove(int id) {
        String result = "Not possible to remove the client";
        try {
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("delete from client where cpf = ?");
            ps.setInt(1, id);
            int numberRows = ps.executeUpdate();
            if (numberRows > 0) {
                result = "Client has been removed";
            }
            ps.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (PSQLException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Client getObject(int id) {
        Client client = null;
        try {
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("select * from client where cpf = ?");
            ps.setInt(1, id);
            ResultSet myResult = ps.executeQuery();
            while (myResult.next()) {
                client = myFactory.create("Client");
                client.setCpf(myResult.getInt(1));
                client.setName(myResult.getString(2));
                client.setAddress(myResult.getString(3));
            }
            myResult.close();
            ps.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (PSQLException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return client;
    }

    @Override
    public String update(String field, String value, int cpf) {
        String result = "not possible update the client";
        try {
            Connection conn = makeConnection.makeConnection();
            PreparedStatement ps = conn.prepareStatement("update table client set ? = ? where cpf = ?");
            ps.setString(1, field);
            ps.setString(2, value);
            ps.setInt(3, cpf);

            int numberRows = ps.executeUpdate();
            if (numberRows > 0) {
                result = "Client has been updated";
            }
            ps.close();
            conn.close();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (PSQLException e) {
            result = "";
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean thisClientExist(int cpf) {
        boolean result = false;

            try {
                Connection conn = makeConnection.makeConnection();
                CallableStatement proc = conn.prepareCall("{call fnc_thiscpfexists(?)}");
                proc.setInt(1, cpf);
                ResultSet rs = proc.executeQuery();

                int value = 0;
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                if (value > 0) {
                    result = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }

    }