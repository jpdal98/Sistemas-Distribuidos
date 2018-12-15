package com.company.TCPClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.TimeoutException;

public class TCPClient {
    private String ip;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;
    private Socket clientSocket;
    private static TCPClient myself;
    private TCPClient(){
        ip = "LocalHost";
        port=5000;
        try {
            clientSocket = new Socket(ip, port);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Initiazed");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    public static TCPClient getInstance(){
        if(myself == null){
            myself = new TCPClient();
            return  myself;
        }
        return myself;
    }

    public boolean send(String packageToSend) {
        boolean result = false;
        try {

            out.writeUTF(packageToSend);
            result = true;
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println("Variable not initiliazed");
        }
        return result;
    }
    public String receive(){
        String result = null;
        try{
            result = in.readUTF();
        }
        catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }
        catch (NullPointerException e){
            System.out.println(e.getCause());
        }
        catch(IOException e) {System.out.println("readline:"+e.getMessage());}

        return result;
    }
    public void closeConnection(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}