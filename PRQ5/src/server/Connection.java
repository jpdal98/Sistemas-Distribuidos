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
	    	String retorno;
	    	Calculadora calc = new Calculadora();
	    	Capitalizador cap = new Capitalizador();
	
	    	String data = in.readUTF();
	    	
	    	String[] dataSplited = data.split(" ");
	    	
	    	if(dataSplited[0].equals("cap")) {
	    		String msgRange = data.substring(4, data.length());
	    		retorno = cap.capitalizar(msgRange); 
	    	} else {
	    		retorno = calc.process(data);
	    	}
	    	
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
