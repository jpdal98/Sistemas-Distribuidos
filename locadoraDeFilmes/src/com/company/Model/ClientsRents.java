package com.company.Model;

import java.util.ArrayList;

public class ClientsRents {
    private Client client;
    private ArrayList<Movie> clientsMovies;

    public ClientsRents(){
        setClientsMovies(new ArrayList<>());
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Movie> getClientsMovies() {
        return clientsMovies;
    }

    public void setClientsMovies(ArrayList<Movie> clientsMovies) {
        this.clientsMovies = clientsMovies;
    }
    public void addMovie(Movie movie){
        this.clientsMovies.add(movie);
    }
}
