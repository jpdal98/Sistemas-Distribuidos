package com.company.Screens;


import com.company.Factory.Factory;
import com.company.Factory.FactoryClient;
import com.company.Main;
import com.company.Model.Client;
import com.company.Model.ClientsRents;
import com.company.Model.Movie;
import com.company.Model.Rent;

import java.sql.Date;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MainScreenClerk extends MainScreen {
    private ClerkControl myControl;
    public MainScreenClerk(){
        myControl  = new ClerkControl();
    }

    public  void help(){
        System.out.println("-----------Menu---------------------");
        System.out.println("|1- Adicionar um filme     |");
        System.out.println("|2- Remover um filme       |");
        System.out.println("|3- listar todos os filmes |");
        System.out.println("|4- Pesquisa um filme      |");
        System.out.println("|5- Adicionar um alguel    |");
        System.out.println("|6- Remove aluguel         |");
        System.out.println("|7- Pesquisar um alguel de um cliente |");
        System.out.println("|8- Listar todos os clientes que possuem aluguel |");
        System.out.println("|9- Adicionar um cliente          |" );
        System.out.println("|10- Remover um cliente           |");
        System.out.println("|11- Listar todos os clientes     |");
        System.out.println("|12- Pesquisar um client (Done)   |");
        System.out.println("|13- Sair(Done)                   |");
        System.out.println("|Digite help para mostrar as opções|");
        System.out.println("------------------------------------");
    }
    @Override
    public void show() {
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        String answerClient;
        int count = 0;
        help();
        while (flag) {

            System.out.print(">>Digite uma opção:");
            answerClient = in.nextLine();

            switch (answerClient) {
                case "1":
                    System.out.print(">>Digite o codigo do filme: ");
                    String movieCode = in.nextLine();

                    System.out.println();

                    System.out.print(">>Digite o titulo do filme: ");
                    String movieTitle = in.nextLine();

                    System.out.println("Digite a descrição do filme");
                    String movieDescription = in.nextLine();

                    System.out.println("Digite a quantidade do filme");
                    String movieQuantity = in.nextLine();

                    System.out.println("Digite o preço do livro");
                    String moviePrice = in.nextLine();

                    String resultMovie = movieCode + "," + movieTitle + "," + movieDescription+","+movieQuantity+","+moviePrice;
                    System.out.println(myControl.addMovie(resultMovie));
                    break;
                case "2":
                    System.out.println("Digite o  id do livro");
                     movieCode= in.nextLine();

                    System.out.println(myControl.removeMovie(movieCode));
                    break;
                case "3":
                    ArrayList<Movie> movies = new ArrayList<>();

                    movies = myControl.getAllMovies();
                    count=0;
                    count++;
                    for(Movie movie: movies){
                        System.out.println();
                        System.out.println(+count+") "+"Code: "+ movie.getCode()+ " Title: "+movie.getTitle()+" Quantity: "+movie.getQuantity()+" Price "+movie.getValuemovie()+"\n Description: \n"+movie.getDescription());
                        count++;
                    }
                    break;
                case "4":
                    System.out.println("Digite o codigo do filme");
                    movieCode = in.nextLine();
                    Movie movie = myControl.searchMovie(movieCode);
                    if(movie!=null){
                        System.out.println("Code: "+ movie.getCode()+ " Title: "+movie.getTitle()+" Quantity: "+movie.getQuantity()+" Price: "+movie.getValuemovie()+"\n Description: \n"+movie.getDescription());
                    }
                    else{
                        System.out.println("Esse filme não existe");
                    }
                       break;
                case "5":
                    System.out.println("Digite o cpf");
                    String cpf = in.nextLine();

                    System.out.println("Digite o codigo do filme");
                    movieCode = in.nextLine();

                    System.out.println("Digite a data de alguel no  modelo(d/m/y)");
                    String dayRent = in.nextLine();

                    System.out.println("Digite a data de devolução no  modelo(d/m/y)");
                    String dayToGiveBack = in.nextLine();

                    String resultRent = cpf+","+movieCode+","+dayRent+","+dayToGiveBack+",";
                    boolean thisClientExists = myControl.clientExits(cpf);
                    if(thisClientExists){
                        boolean canClientRent = myControl.canClientRent(cpf);
                        if(canClientRent){
                            boolean hasEnouthQuantity = myControl.hasEnouthQuantity(movieCode);
                            if(hasEnouthQuantity){
                                System.out.println(myControl.addRent(resultRent));
                                System.out.println(">> Gerar nota fiscal? 1-sim 2-não");
                                String answerInvoice = in.nextLine();

                                switch (answerInvoice){
                                    case "1":
                                        Rent rent = myControl.getInvoice(movieCode,cpf);
                                        System.out.println("Cliente Nome: "+ rent.getName()+ " CPF: "+rent.getCpf()+" Filme Nome: "+rent.getMovieName()+" Filme Code: "+rent.getCodeMovie()+" Data do aluguel: "+rent.getDateRent()+" Data de devolução "+rent.getDateToGiveBack());
                                        break;
                                    case "2": break;
                                    default:
                                        System.out.println("Essa opção não existe");
                                        break;
                                }
                            }else{
                                System.out.println("A quantidade de filmes em estoque foi esgotada");
                            }
                        }
                        else{
                            System.out.println("O cliente esgotou a quantidade de aluguels");
                        }
                    }else{
                        System.out.println("Cliente não existente");
                    }
                    break;
                    case "6":
                        //Remove a rent
                        System.out.println("Digite o número do cpf");
                        cpf = in.nextLine();
                        thisClientExists = myControl.clientExits(cpf);
                        if(thisClientExists){
                            System.out.println("Digite o codigo do filme");
                            movieCode = in.nextLine();
                         boolean   thisMoviesExits = myControl.movieExists(movieCode);
                            if(thisMoviesExits){
                                System.out.println(myControl.removeRent(cpf,movieCode));
                            }else{
                                System.out.println("Desculpe, mas esse filme não existe!");
                            }

                        }else{
                            System.out.println("Desculpe, mas esse cliente não existe");
                        }
                    break;
                case "7":
                    System.out.println("Digite o número do cpf");
                    cpf = in.nextLine();
                    thisClientExists = myControl.clientExits(cpf);
                    if(thisClientExists){
                        ClientsRents clientsRent = myControl.getRentsClient(cpf);
                        System.out.println("Nome: "+clientsRent.getClient().getName()+ " Codigo: "+clientsRent.getClient().getCpf());
                        for (Movie movieRe: clientsRent.getClientsMovies()){
                            System.out.println("Codigo: "+movieRe.getCode()+" Titulo: "+movieRe.getTitle());
                        }
                    }
                    else{
                        System.out.println("Desculpe, mas esse cliente não existe");
                    }
                    break;
                case "8":
                    ArrayList<ClientsRents> arrayListRented;
                    arrayListRented = myControl.getAllRents();
                    count = 0;
                    count++;
                    for (ClientsRents clientsRents: arrayListRented) {
                        System.out.println(+count+") "+" Name: "+ clientsRents.getClient().getName()+ " CPF: "+ clientsRents.getClient().getCpf());
                        for (Movie movieG: clientsRents.getClientsMovies()){
                            System.out.println("Code: "+ movieG.getCode()+ " Title: "+movieG.getTitle());
                        }
                        count++;
                    }
                    break;
                case "9":
                    System.out.println("Digite o nome do cliente");
                    String nameClient = in.nextLine();

                    System.out.println("Digite o cpf do cliente: ");
                    cpf = in.nextLine();

                    System.out.println("Digite o endereço do cliente");
                    String clientAddress = in.nextLine();

                    String resultClient = cpf + "," + nameClient + "," + clientAddress;

                    System.out.println(myControl.addClient(resultClient));
                    break;
                case "10" :
                    System.out.println("Digite o cpf do cliente");
                    cpf= in.nextLine();
                    thisClientExists = myControl.clientExits(cpf);
                    if(thisClientExists){
                        System.out.println(myControl.removeClient(cpf));
                    }else{
                        System.out.println("Desculpe, mas esse cliente não existe no sistema");
                    }
                    break;

                case "11":
                    ArrayList<Client> clients = new ArrayList<>();

                    clients = myControl.getAllClients();
                    count=0;
                    count++;
                    for(Client client: clients){
                        System.out.println();
                        System.out.println(+count+") "+" Nome: "+ client.getName()+ " CPF: "+client.getCpf()+" Endereço: "+client.getAddress());
                        count++;
                    }

                    break;
                case "12":
                    System.out.println("Digite o cpf do cliente");
                    cpf = in.nextLine();
                    Client client = myControl.searchClient(cpf);
                    if(client!=null){
                        System.out.println("Nome: "+ client.getName()+ " CPF: "+client.getCpf()+" Endereço: "+client.getAddress());
                    }
                    else{
                        System.out.println("Esse cpf não existe");
                    }


                    break;
                case "13":
                    flag = false;
                    Main.flag = false;
                    System.out.println("Obrigado por ultilizar nosso programa.");
                    break;
                    case "help":
                        help();
                        break;

                default:
                    System.out.println("Essa resposta não existe");
                    break;
            }
        }
    }
}
