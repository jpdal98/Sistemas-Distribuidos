package com.company.Screens;

import com.company.Factory.FactoryUser;
import com.company.Main;
import com.company.Model.User;
import com.company.Proxy.UserProxy;
import com.company.TCPClient.TCPClient;

import java.util.Scanner;

public class InitialScreen extends Screen {
    private UserProxy myControler;
    private FactoryUser myFactory;

    public InitialScreen(){
        myControler = new UserProxy();
        myFactory = new FactoryUser();
    }

    @Override
    public void show() {
        Scanner in = new Scanner(System.in);
        System.out.println("Olá, você desejar logar  ou cadastra-se no sistema");
        boolean flag = true;
        while(flag) {
            System.out.println("Digite 1-logar ou 2-cadastra no sistema ou 3- para sair");
            String answer = in.nextLine();
            switch (answer) {
                case "1":
                    System.out.println("Por favor digite o login");
                    String userLogin = in.nextLine();
                    System.out.println("Por favor digite a senha");
                    String passwordLogin = in.nextLine();
                    int userID = myControler.checkPasssUser(userLogin,passwordLogin);
                    if (userID!=-1) {
                        int levelOfUser = myControler.checkUserLevel(userID);
                        if(levelOfUser == 1){
                            User user = myControler.returnUser(userID);
                            myControler.switchScreen("MainScreenClerk",user);
                            flag = false;
                        }
                        else{
                            User user = myControler.returnUser(userID);
                            myControler.switchScreen("MainScreenADM",user);
                            flag = false;
                        }
                    } else {
                        System.out.println("Desculpe, mas o usuario ou senha estao incorretos");
                    }
                    break;
                case "2":
                    System.out.println("Escreva o seu cpf");
                    String cpf = in.nextLine();
                    System.out.println("Escreva seu nome");
                    String name = in.nextLine();
                    System.out.println("Escreva seu userName");
                    String userName = in.nextLine();
                    System.out.println("Escreva sua senha");
                    String password = in.nextLine();
                    System.out.println("Escreva seu endereco");
                    String address = in.nextLine();

                    String messager = cpf+","+name+","+userName+","+password+","+address;
                    boolean result = myControler.insertUser(messager);

                    if(result){
                        System.out.println("Usuario cadastro com sucesso");
                    }
                    else{
                        System.out.println("Usuario nao pode ser cadastrado");
                    }
                    break;
                case "3":
                    flag = false;
                    Main.flag = false;
                    break;
                default:
                    System.out.println("Opção não existente, por favor digite uma opção correta");
                    break;
            }
        }
        System.out.println();


    }

}
