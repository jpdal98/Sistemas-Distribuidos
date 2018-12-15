package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
	    try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
	    } catch(IOException e) {
	    	System.out.println("Connection IOException: " + e.getMessage());
	    }
	}
	
	public void run(){
	    try {			                 
	    	String data = in.readUTF();
	    	
	    	Calculadora calc = new Calculadora();
	    	String retorno = calc.process(data);
	    	
	    	out.writeUTF(retorno);
	    } catch(EOFException e) { 
	    	System.out.println("Connection EOFException: " + e.getMessage());
	    } catch(IOException e) { 
	    	System.out.println("Connection IOException: " + e.getMessage());
	    } finally { 
	    	try { 
	    		clientSocket.close();
	    	} catch (IOException e){ 
	    		System.out.println("Connection IOException: " + e.getMessage()); 
	    	}
	    }
	}
}
