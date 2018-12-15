package com.company.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer  {
    public static void main(String args[]) {

        try {
            DataInputStream in;
            DataOutputStream out;

            int serverPort = 5000;
            Despachante despachante = new Despachante();
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("Server ready to receive");
                while (!clientSocket.isClosed()) {
                    String data = receberMsg(clientSocket, in);
                    String result = despachante.selecionaEqueleto(data);
                    enviar(result, out);
                }
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }

    }
    public static String receberMsg(Socket clientSocket,DataInputStream in) {
        String mensagem = null;
        try {
            mensagem = in.readUTF();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println("Null point are being accessed");
        }
        return mensagem;
    }
    public static void enviar(String mensagem,DataOutputStream out) {
        try {
            out.writeUTF(mensagem);
        }
        catch (NullPointerException e){

            System.out.println("Null point tcp server out ");
        }
        catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }
}
