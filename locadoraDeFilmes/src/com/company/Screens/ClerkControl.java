package com.company.Screens;

import com.company.DAO.ClientDAO;
import com.company.DAO.DAO;
import com.company.DAO.MovieDAO;
import com.company.DAO.RentDAO;
import com.company.Factory.Factory;
import com.company.Factory.FactoryClient;
import com.company.Factory.FactoryMovie;
import com.company.Factory.FactoryRent;
import com.company.Model.*;
import com.company.Proxy.UserProxy;

import java.util.ArrayList;

public class ClerkControl {
    private DAO myDao;
    private Factory myFactory;
    private UserProxy myProxy;


    public boolean movieHasQuantity(String movieCode){
        myDao = new MovieDAO();
        return ((MovieDAO) myDao).movieHasQuantity(Integer.parseInt(movieCode));
    }
    public String addRent(String rent){
        String[] rentArray = rent.split(",");
        myDao = new RentDAO();
        myFactory = new FactoryRent();
        String result;
        Rent newRent= ((FactoryRent) myFactory).create("Rent");

        newRent.setCpf(Integer.parseInt(rentArray[0]));
        newRent.setCodeMovie(Integer.parseInt(rentArray[1]));
        newRent.setDateRent(rentArray[2]);
        newRent.setDateToGiveBack(rentArray[3]);

        result = myDao.add(newRent);

        return result;
    }

    public String addMovie(String movie){
        String[] movieArray = movie.split(",");
        myDao = new MovieDAO();
        myFactory = new FactoryMovie();
        String result;
        Movie newMovie = ((FactoryMovie) myFactory).create("Movie");

        newMovie.setCode(Integer.parseInt(movieArray[0]));
        newMovie.setTitle(movieArray[1]);
        newMovie.setDescription(movieArray[2]);
        newMovie.setQuantity(Integer.parseInt(movieArray[3]));
        newMovie.setValuemovie(Float.parseFloat(movieArray[4]));

        result = myDao.add(newMovie);

        return result;
    }
    public String addClient(String client){
        String[] clientArray = client.split(",");
        myDao = new ClientDAO();
        myFactory = new FactoryClient();
        String result;
        Client newClient = ((FactoryClient) myFactory).create("Client");

        newClient.setCpf(Integer.parseInt(clientArray[0]));
        newClient.setName(clientArray[1]);
        newClient.setAddress(clientArray[2]);

        result = myDao.add(newClient);

        return result;
    }
    public ArrayList<Client> getAllClients(){
        myDao = new ClientDAO();
        return myDao.getAll();
    }
    public ArrayList<ClientsRents> getAllRents(){
        myDao = new RentDAO();
        return ((RentDAO) myDao).getALLClientsRents();
    }
    public ClientsRents getRentsClient(String cpf){
        myDao = new RentDAO();
        return ((RentDAO) myDao).getClientRents(Integer.parseInt(cpf));
    }

    public boolean updateClient(String client){
        return false;
    }

    public String updateMovie(String field,String movieQTD,String movieID){
        myDao = new MovieDAO();
        return myDao.update(field,movieQTD,Integer.parseInt(movieID));

    }

    //////////////////////////////////////////////
    public String removeClient(String idClient){
        myDao = new ClientDAO();
        String result;


         result = myDao.remove(Integer.parseInt(idClient));

        return result;
    }
    public String removeMovie(String idMovie){
        myDao = new MovieDAO();
        String result;

        result = myDao.remove(Integer.parseInt(idMovie));
        return result;
    }
    public String removeAllRent(String cpf){
        myDao = new RentDAO();
        return ((RentDAO) myDao).removeByClient(Integer.parseInt(cpf));
    }
    public String removeRent(String cpf,String idmovie){
        myDao = new RentDAO();
        return ((RentDAO) myDao).removeNormal(Integer.parseInt(idmovie),Integer.parseInt(cpf));
    }

    ////////////////////////////////////////////
    public Movie searchMovie(String idMovie){
        myDao = new MovieDAO();
        return (Movie)myDao.getObject(Integer.parseInt(idMovie));
    }
    public boolean movieExists(String idmovie){
        myDao = new MovieDAO();
        return ((MovieDAO) myDao).thisMoviesExists(Integer.parseInt(idmovie));
    }

    public Client searchClient(String idClient){
       myDao = new ClientDAO();
       return (Client) myDao.getObject(Integer.parseInt(idClient));
    }
    public Rent getInvoice(String idmovie,String cpf){

        myDao = new RentDAO();
        return ((RentDAO) myDao).getInvoice(Integer.parseInt(idmovie),Integer.parseInt(cpf));
    }

    public ArrayList<Movie> getAllMovies(){
        myDao = new MovieDAO();
        return myDao.getAll();
    }
    public boolean clientExits(String cpf){
        myDao = new ClientDAO();
        return ((ClientDAO) myDao).thisClientExist(Integer.parseInt(cpf));
    }
    public boolean canClientRent(String cpf){
        myDao = new RentDAO();
        return ((RentDAO) myDao).canRentMovie(Integer.parseInt(cpf));
    }

    public boolean hasEnouthQuantity(String movieCode){
        myDao = new MovieDAO();
        return ((MovieDAO) myDao).movieHasQuantity(Integer.parseInt(movieCode));
    }
    public String searchUser(String idUser){
        String result;
        myProxy = new UserProxy();
        User user  = myProxy.returnUser(Integer.parseInt(idUser));
        if(user==null){
            result= "Nao foi possivel encontrar esse usuario";
        }
        else{
            result = "CPF: "+user.getCPF()+" \n Nome: "+user.getName()+" \n"+" Endereco "+user.getAddress()+"\n Salario: "+user.getSalary();
        }
        return result;
    }

}
